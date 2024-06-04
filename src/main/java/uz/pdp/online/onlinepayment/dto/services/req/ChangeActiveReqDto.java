package uz.pdp.online.onlinepayment.dto.services.req;

import jakarta.validation.constraints.Pattern;
import lombok.Value;

@Value
public class ChangeActiveReqDto {

    @Pattern(regexp = "[0-9]{6}", message = "Category Number must be 6 number")
    String number;

    boolean active;

}
