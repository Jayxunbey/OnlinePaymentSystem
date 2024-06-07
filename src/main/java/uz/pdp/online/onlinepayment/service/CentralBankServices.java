package uz.pdp.online.onlinepayment.service;

import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.Transfers.FailedTransactionException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardNotFoundException;
import uz.pdp.online.onlinepayment.dto.betweens.PlasticCardDetailsDto;
import uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CentralBankServices {

    Map<String, PlasticCardDetailsDto> plasticCardDetailsDtoMap = new HashMap<>();

    {
        try {
            plasticCardDetailsDtoMap.put("8600457569451515",
                    new PlasticCardDetailsDto("8600457569451515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),new BigDecimal("565569.6645"),"UZCARD"));
            plasticCardDetailsDtoMap.put("9860547515665162",
                    new PlasticCardDetailsDto("9860547515665162","Mirjalol Qosimov", "+998974574566","Ravnaq Bank","65645556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2020"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2025"),new BigDecimal("9656554.6645"),"HUMO"));
            plasticCardDetailsDtoMap.put("9860147565846321",
                    new PlasticCardDetailsDto("9860147565846321","Nosirbek Muxammadsharipov", "+998884548556","Xalq Bank","65699575566126236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.15.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.15.2026"),new BigDecimal("2545.5454"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600752637859015",
                    new PlasticCardDetailsDto("8600752637859015","Ravshan Namozov", "+998508966587","SQB Bank","65699559595626236994",false, new SimpleDateFormat("dd.MM.yyyy").parse("14.11.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("14.11.2026"),new BigDecimal("565.548"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600478521455566",
                    new PlasticCardDetailsDto("8600478521455566","Sanjar Maripov", "+998908879031","WEST Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2019"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2024"),new BigDecimal("78.5"),"UZCARD"));
            plasticCardDetailsDtoMap.put("9860151542243265",
                    new PlasticCardDetailsDto("9860151542243265","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",false, new SimpleDateFormat("dd.MM.yyyy").parse("13.06.2020"),new SimpleDateFormat("dd.MM.yyyy").parse("13.06.2025"),new BigDecimal("98956454.54"),"HUMO"));
            plasticCardDetailsDtoMap.put("9860789545652365",
                    new PlasticCardDetailsDto("9860789545652365","Umidjon Qurbonov", "+998200154886","Ravnak Bank","65645556565626236994",false, new SimpleDateFormat("dd.MM.yyyy").parse("25.09.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("25.09.2026"),new BigDecimal("989564.45"),"HUMO"));
            plasticCardDetailsDtoMap.put("8600456432471515",
                    new PlasticCardDetailsDto("8600456432471515","Jayxunbek Muxammadov", "+998934577566","TBC Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("29.05.2023"),new SimpleDateFormat("dd.MM.yyyy").parse("29.05.2028"),new BigDecimal("98388.448"),"UZCARD"));
            plasticCardDetailsDtoMap.put("9860350154789536",
                    new PlasticCardDetailsDto("9860350154789536","Akrombek Sodiqov", "+998759956659","Agro Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("16.02.2024"),new SimpleDateFormat("dd.MM.yyyy").parse("16.02.2029"),new BigDecimal("76564645.5484"),"HUMO"));
            plasticCardDetailsDtoMap.put("8600659845874632",
                    new PlasticCardDetailsDto("8600659845874632","Akrombek Sodiqov", "+998975856595","Turon Bank","65699556565626298994",true, new SimpleDateFormat("dd.MM.yyyy").parse("14.03.2022"),new SimpleDateFormat("dd.MM.yyyy").parse("14.03.2027"),new BigDecimal("685324.64"),"UZCARD"));
            plasticCardDetailsDtoMap.put("9860789546825484",
                    new PlasticCardDetailsDto("9860789546825484","Jayxunbek Muxammadov", "+998200005045","SQB Bank","65699559595626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("28.07.2022"),new SimpleDateFormat("dd.MM.yyyy").parse("28.07.2027"),new BigDecimal("15887654.4"),"HUMO"));
            plasticCardDetailsDtoMap.put("8600457869451515",
                    new PlasticCardDetailsDto("8600457869451515","Abdulhafiz Shamatov", "+998995426566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("22.09.2024"),new SimpleDateFormat("dd.MM.yyyy").parse("22.09.2029"),new BigDecimal("9656554.78"),"UZCARD"));
            plasticCardDetailsDtoMap.put("9860457569451215",
                    new PlasticCardDetailsDto("9860457569451215","Abdulhafiz Shamatov", "+998995426566","QQB Bank","65699557865626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("16.08.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("16.08.2026"),new BigDecimal("848984.6546"),"HUMO"));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


    public PlasticCardDetailsDto checkAndGetPlasticCard(String plasticNumber, Date dateViaParseFrom, String phoneNumber) {

        PlasticCardDetailsDto plasticCardDetailsDto = plasticCardDetailsDtoMap.get(plasticNumber);
        if (plasticCardDetailsDto!=null &&
                plasticCardDetailsDto.getExpirationDate().compareTo(dateViaParseFrom)==0) {
            return plasticCardDetailsDto;
        }
        else {
            return null;
        }


    }

    public void transferMoney(PlasticCard fromPlasticCard, String to, BigDecimal totalAmount) {

        if (!sendMoneyTo(to, totalAmount)) {
            throw new FailedTransactionException(to);
        }

        if (!withdrawMoneyFromAccountOfCurrentlyUser(fromPlasticCard,totalAmount)) {
            throw new FailedTransactionException(to);
        }

    }

    private boolean withdrawMoneyFromAccountOfCurrentlyUser(PlasticCard fromPlasticCard, BigDecimal totalAmount) {
        PlasticCardDetailsDto plasticCardDetailsDto = plasticCardDetailsDtoMap.get(fromPlasticCard.getNumber());
        if (plasticCardDetailsDto == null) {
            throw new PlasticCardNotFoundException();
        }

        BigDecimal subtractedBalance = plasticCardDetailsDto.getBalance().subtract(totalAmount);

        plasticCardDetailsDto.setBalance(subtractedBalance);

        plasticCardDetailsDtoMap.put(fromPlasticCard.getNumber(), plasticCardDetailsDto);
        return true;

    }

    private boolean sendMoneyTo(String to, BigDecimal totalAmount) {
        PlasticCardDetailsDto remotePlasticCard = plasticCardDetailsDtoMap.get(to);
        if (remotePlasticCard == null) {
            throw new PlasticCardNotFoundException();
        }

        BigDecimal added = remotePlasticCard.getBalance().add(totalAmount);

        remotePlasticCard.setBalance(added);

        plasticCardDetailsDtoMap.put(to, remotePlasticCard);
        return true;

    }
}
