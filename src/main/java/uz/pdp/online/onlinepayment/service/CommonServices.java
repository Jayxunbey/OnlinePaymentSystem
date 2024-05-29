package uz.pdp.online.onlinepayment.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CommonServices {

    public Date getDateViaParseFrom(String dateString, String regex) throws ParseException {

        return new SimpleDateFormat(regex).parse(dateString);

    }
}
