package uz.pdp.online.onlinepayment.dto.signup.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlasticCardResponseDto implements Serializable {

    @JsonProperty("number")
    String number;

    @JsonProperty("owner_name")
    String ownerName;

    @JsonProperty("bank_name")
    String bankName;

    @JsonProperty("card_name")
    String cardName;

    @JsonProperty("expiration_date")
    String expirationDate;

    String type;

    Double balance;
}