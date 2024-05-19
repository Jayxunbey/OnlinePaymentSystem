package uz.pdp.online.onlinepayment.dto.jay.signup.req;


import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class UserSignUpConfirmDto implements Serializable {
    @NotNull
    public String token;
    @NotNull
    public String confirmation_code;

}
