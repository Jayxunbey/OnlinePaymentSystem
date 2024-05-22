package uz.pdp.online.onlinepayment.dto.signup.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldErrorDTO {

    private String field;
    private String message;

}
