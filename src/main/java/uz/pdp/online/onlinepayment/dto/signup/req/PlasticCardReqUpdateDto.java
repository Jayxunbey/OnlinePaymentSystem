package uz.pdp.online.onlinepayment.dto.signup.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlasticCardReqUpdateDto implements Serializable {

    @Pattern(message = "plastic card number is not valid", regexp = "[0-9]{16}")
    String number;

    @NotBlank
    @NotEmpty
    String cardName;
}