package uz.pdp.online.onlinepayment.dto.signup.resp;

import lombok.Value;
import uz.pdp.online.onlinepayment.entity.inpostgres.RegistirationTempSentCode;

import java.io.Serializable;

/**
 * DTO for {@link RegistirationTempSentCode}
 */
@Value
public class RegistirationTempSentCodeRespDto implements Serializable {
    String token;
    Integer expiration;
}