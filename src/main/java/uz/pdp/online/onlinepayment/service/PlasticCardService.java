package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.onlinepayment.dto.signup.req.PlasticCardAddReqDto;

import java.text.ParseException;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class PlasticCardService {

    private final CommonServices commonServices;
    private CentralBankServices centralBankServices;

    public void addPlasticCard(PlasticCardAddReqDto plasticCardAddReqDto) throws ParseException {

        String bankName = plasticCardAddReqDto.getBankName().strip();

        String expirationString = plasticCardAddReqDto.getExpiration();

        String plasticNumber = plasticCardAddReqDto.getNumber();

        Date dateViaParseFrom = commonServices.getDateViaParseFrom(expirationString, "dd/MM/yyyy");

        centralBankServices.checkAndGetPlasticCard(plasticNumber,dateViaParseFrom);

        if ()

    }
}
