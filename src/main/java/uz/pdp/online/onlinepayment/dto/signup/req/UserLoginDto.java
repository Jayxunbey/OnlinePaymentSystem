package uz.pdp.online.onlinepayment.dto.signup.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

import java.io.Serializable;

@Value
public class UserLoginDto implements Serializable{
    @NotNull
    @Pattern(regexp = "^\\+998\\d{9}$", message = "phone is not valid")
    String phone;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "password must be minimum 8 characters, at least one letter and one number")
    String password;

    @NotNull
    boolean rememberMe;
}
