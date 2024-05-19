package uz.pdp.online.onlinepayment.dto.jay.signup.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.online.onlinepayment.entity.inpostgres.User}
 */
@Value
public class UserSignUpDto implements Serializable {
    @NotNull
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9]{4,19}$", message = "Username must be between [5,20] and starts with character")
    String name;

    @NotNull
    @Pattern(regexp = "^\\+998\\d{9}$", message = "phone is not valid")
    String phone;

    @NotNull
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,}$", message = "password must be minimum 8 characters, at least one letter and one number")
    String new_password;

    @NotNull
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,}$", message = "password must be minimum 8 characters, at least one letter and one number")
    String repeat_password;

}