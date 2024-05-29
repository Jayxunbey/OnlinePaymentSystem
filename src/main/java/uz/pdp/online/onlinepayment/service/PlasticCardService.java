package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.PlasticCardNotFoundException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.PlasticCardPhoneNumberNotEqualException;
import uz.pdp.online.onlinepayment.dto.betweens.PlasticCardDetailsDto;
import uz.pdp.online.onlinepayment.dto.signup.req.PlasticCardAddReqDto;

import java.text.ParseException;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class PlasticCardService {

    private final CommonServices commonServices;
    private final CentralBankServices centralBankServices;

    public void addPlasticCard(PlasticCardAddReqDto plasticCardAddReqDto) throws ParseException {

        String bankName = plasticCardAddReqDto.getBankName().strip();

        String expirationString = plasticCardAddReqDto.getExpiration();

        String plasticNumber = plasticCardAddReqDto.getNumber();

        Date dateViaParseFrom = commonServices.getDateViaParseFrom(expirationString, "dd/MM/yyyy");

        PlasticCardDetailsDto plasticCardDetailsDto = centralBankServices.checkAndGetPlasticCard(plasticNumber, dateViaParseFrom);

        if (plasticCardDetailsDto==null){
            throw new PlasticCardNotFoundException();
        }


        String phone = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String phoneNumber = plasticCardDetailsDto.getPhoneNumber();

        if (!phoneNumber.equals(phone)) {
            throw new PlasticCardPhoneNumberNotEqualException();
        }



        System.out.println("cardPhoneNumber.equals(secPhone) = " + phoneNumber.equals(phone));




    }
}
