package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardAlreadyExistException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardNotFoundException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardPhoneNumberNotEqualException;
import uz.pdp.online.onlinepayment.dto.betweens.PlasticCardDetailsDto;
import uz.pdp.online.onlinepayment.dto.signup.req.PlasticCardAddReqDto;
import uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard;
import uz.pdp.online.onlinepayment.entity.inpostgres.User;
import uz.pdp.online.onlinepayment.repo.inpostgres.PlasticCardRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

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

        checkPlasticThrough(plasticNumber);

        Date dateViaParseFrom = commonServices.getDateViaParseFrom(expirationString, "dd/MM/yyyy");

        String phone = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        PlasticCardDetailsDto plasticCardDetailsDto = centralBankServices.checkAndGetPlasticCard(plasticNumber, dateViaParseFrom, phone);

        if (plasticCardDetailsDto == null) {
            throw new PlasticCardNotFoundException();
        }

        if (!plasticCardDetailsDto.getPhoneNumber().equals(phone)){
            throw new PlasticCardPhoneNumberNotEqualException();
        }

        // Plastic card mavjud va amallar bajarish mumkin

        User user = userService.getUserByPhoneNumber(phone);

        PlasticCard entity = getAsEntityFrom(plasticCardDetailsDto, user,cardName);

        plasticCardRepository.save(entity);


    }

    private void checkPlasticThrough(String plasticNumber) {
        Optional<PlasticCard> byNumber = plasticCardRepository.findByNumber(plasticNumber);
        if (byNumber.isPresent()) {
            throw new PlasticCardAlreadyExistException();
        }
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
}
