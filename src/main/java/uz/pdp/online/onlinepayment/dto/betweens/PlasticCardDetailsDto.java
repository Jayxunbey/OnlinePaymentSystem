package uz.pdp.online.onlinepayment.dto.betweens;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO for {@link uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard}
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PlasticCardDetailsDto implements Serializable {
    String number;
    String ownerName;
    String phoneNumber;
    String bankName;
    String bankAccountNumber;
    Boolean active;
    Date issuedDate;
    Date expirationDate;
    BigDecimal balance;
    String type;
}