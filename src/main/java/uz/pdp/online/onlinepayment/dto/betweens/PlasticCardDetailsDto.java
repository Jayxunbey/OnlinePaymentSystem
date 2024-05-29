package uz.pdp.online.onlinepayment.dto.betweens;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlasticCardDetailsDto implements Serializable {
    String number;
    String ownerName;
    String phoneNumber;
    String bankName;
    String bankAccountNumber;
    Boolean active;
    Date issuedDate;
    Date expirationDate;
    String type;

}