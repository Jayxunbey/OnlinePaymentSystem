package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardAlreadyExistException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardNotFoundException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardPhoneNumberNotEqualException;
import uz.pdp.online.onlinepayment.dto.betweens.PlasticCardDetailsDto;
import uz.pdp.online.onlinepayment.dto.resp.PlasticCardResponseDto;
import uz.pdp.online.onlinepayment.dto.signup.req.PlasticCardAddReqDto;
import uz.pdp.online.onlinepayment.dto.signup.req.PlasticCardReqUpdateDto;
import uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard;
import uz.pdp.online.onlinepayment.entity.inpostgres.User;
import uz.pdp.online.onlinepayment.repo.inpostgres.PlasticCardRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PlasticCardService {

    private final CommonServices commonServices;
    private final CentralBankServices centralBankServices;
    private final PlasticCardRepository plasticCardRepository;
    private final UserService userService;
    private final Random random;

    public void addPlasticCard(PlasticCardAddReqDto plasticCardAddReqDto) throws ParseException, AccountNotFoundException {

        String cardName = plasticCardAddReqDto.getCardName().strip();

        String expirationString = plasticCardAddReqDto.getExpiration();

        String plasticNumber = plasticCardAddReqDto.getNumber();

        String securityPhone = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PlasticCard checkPlasticCard = checkPlasticThrough(plasticNumber, securityPhone);

        if (checkPlasticCard !=null) {
            if (checkPlasticCard.getActive()) {
                throw new PlasticCardAlreadyExistException();
            }else {
                checkPlasticCard.setActive(true);
                plasticCardRepository.save(checkPlasticCard);
                return;
            }
        }

        Date dateViaParseFrom = commonServices.getDateViaParseFrom(expirationString, "dd/MM/yyyy");



        PlasticCardDetailsDto plasticCardDetailsDto = centralBankServices.checkAndGetPlasticCard(plasticNumber, dateViaParseFrom, securityPhone);

        if (plasticCardDetailsDto == null) {
            throw new PlasticCardNotFoundException();
        }

        if (!plasticCardDetailsDto.getPhoneNumber().equals(securityPhone)){
            throw new PlasticCardPhoneNumberNotEqualException();
        }

        // Plastic card mavjud va amallar bajarish mumkin

        User user = userService.getUserByPhoneNumber(securityPhone);

        PlasticCard entity = getAsEntityFrom(plasticCardDetailsDto, user,cardName);

        entity.setActive(true);
        plasticCardRepository.save(entity);


    }

    private PlasticCard checkPlasticThrough(String plasticNumber, String phone) {
        Optional<PlasticCard> byNumber = plasticCardRepository.findByNumberAndPhoneNumber(plasticNumber,phone);
        return byNumber.orElse(null);
    }

    private PlasticCard getAsEntityFrom(PlasticCardDetailsDto plasticCardDetailsDto, User user, String cardName) {
        PlasticCard plasticCard = new PlasticCard();

        plasticCard.setNumber(plasticCardDetailsDto.getNumber());
        plasticCard.setActive(true);
        plasticCard.setUser(user);
        plasticCard.setOwnerName(plasticCardDetailsDto.getOwnerName());
        plasticCard.setBankAccountNumber(plasticCardDetailsDto.getBankAccountNumber());
        plasticCard.setPhoneNumber(plasticCardDetailsDto.getPhoneNumber());
        plasticCard.setIssuedDate(plasticCardDetailsDto.getIssuedDate());
        plasticCard.setExpirationDate(plasticCardDetailsDto.getExpirationDate());
        plasticCard.setType(plasticCardDetailsDto.getType());
        plasticCard.setBalance(getBalanceFrom(plasticCardDetailsDto.getNumber()));
        plasticCard.setBankName(plasticCardDetailsDto.getBankName());
        plasticCard.setCardName(cardName);
        return plasticCard;
    }

    private Double getBalanceFrom(String plasticNumber) {
        return random.nextDouble(15, 10000000);
    }

    public List<PlasticCardResponseDto> getAllPlasticCardForResp() {

        List<PlasticCardResponseDto> plasticCardResponseDtos = new ArrayList<>();

        String phone = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<PlasticCard> plasticCards = plasticCardRepository.findByPhoneNumberAndActiveTrue(phone);

        plasticCards.forEach(eachPlasticCard -> {
            plasticCardResponseDtos.add(new PlasticCardResponseDto(
                    eachPlasticCard.getNumber(),
                    eachPlasticCard.getOwnerName(),
                    eachPlasticCard.getBankName(),
                    eachPlasticCard.getCardName(),
                    new SimpleDateFormat("MM/yyyy").format(eachPlasticCard.getExpirationDate()),
                    eachPlasticCard.getType(),
                    eachPlasticCard.getBalance()
            ));
        });

        return plasticCardResponseDtos;

    }


    public void deleteCard(String number) {

        String phoneNumber = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<PlasticCard> plasticCardOptional = plasticCardRepository.findByNumberAndPhoneNumber(number, phoneNumber);

        if (plasticCardOptional.isPresent()) {
            PlasticCard plasticCard = plasticCardOptional.get();
            if (plasticCard.getActive()) {
                plasticCard.setActive(false);
                plasticCardRepository.save(plasticCard);
                return;
            }
        }

        throw new PlasticCardNotFoundException();

    }

    public void updateName(PlasticCardReqUpdateDto plasticCardReqUpdateDto) {

        String phone = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<PlasticCard> byNumberAndPhoneNumber = plasticCardRepository.findByNumberAndPhoneNumber(plasticCardReqUpdateDto.getNumber(), phone);

        if (byNumberAndPhoneNumber.isPresent()) {
            PlasticCard plasticCard = byNumberAndPhoneNumber.get();
            plasticCard.setCardName(plasticCardReqUpdateDto.getCardName());
            plasticCardRepository.save(plasticCard);
            return;
        }

        throw new PlasticCardNotFoundException();

    }
}
