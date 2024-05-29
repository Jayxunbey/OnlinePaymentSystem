package uz.pdp.online.onlinepayment.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CentralBankServices {

    public void checkAndGetPlasticCard(String plasticNumber, Date dateViaParseFrom) {
        Map<String, PlasticCardDetailsDto> plasticCardDetailsDtoMap = new HashMap<>();

        try {
            plasticCardDetailsDtoMap.put("8600457569451515",
                    new PlasticCardDetailsDto("8600457569451515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("9860547515665162",
                    new PlasticCardDetailsDto("9860547515665162","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("9860147565846321",
                    new PlasticCardDetailsDto("8600457569451515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600752637859015",
                    new PlasticCardDetailsDto("8600752637859015","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600478521455566",
                    new PlasticCardDetailsDto("8600478521455566","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("9860151542243265",
                    new PlasticCardDetailsDto("9860151542243265","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("9860789545652365",
                    new PlasticCardDetailsDto("9860789545652365","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600457569471515",
                    new PlasticCardDetailsDto("8600457569471515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("9860350154789536",
                    new PlasticCardDetailsDto("9860350154789536","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600457569451515",
                    new PlasticCardDetailsDto("8600457569451515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600457569451515",
                    new PlasticCardDetailsDto("8600457569451515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600457569451515",
                    new PlasticCardDetailsDto("8600457569451515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600457569451515",
                    new PlasticCardDetailsDto("8600457569451515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600457569451515",
                    new PlasticCardDetailsDto("8600457569451515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600457569451515",
                    new PlasticCardDetailsDto("8600457569451515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));
            plasticCardDetailsDtoMap.put("8600457569451515",
                    new PlasticCardDetailsDto("8600457569451515","Jayxunbek Muxammadov", "+998934577566","Aloqa Bank","65699556565626236994",true, new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2021"),new SimpleDateFormat("dd.MM.yyyy").parse("04.05.2026"),"UZCARD"));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
