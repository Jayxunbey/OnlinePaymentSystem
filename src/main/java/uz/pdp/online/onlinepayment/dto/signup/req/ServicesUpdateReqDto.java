package uz.pdp.online.onlinepayment.dto.signup.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Value;
import uz.pdp.online.onlinepayment.entity.inmongo.Field;

import java.math.BigDecimal;
import java.util.List;

@Value
public class ServicesUpdateReqDto {

    @Pattern(regexp = "[0-9]{6}", message = "CategoryId must be 6 number")
    String categoryId;

    @Pattern(regexp = "[0-9]{6}", message = "Category Number must be 6 number")
    @NotEmpty
    String number;

    @Pattern(regexp = "^[a-zA-Z]{2,}(?: [a-zA-Z]+){0,5}$", message = "Service name must be at least 4 character and max 5 words")
    String name;

    List<Field> fields;

    @NotEmpty
    @NotBlank
    String requestAddress;

    BigDecimal fee;

    BigDecimal cashback;
}
