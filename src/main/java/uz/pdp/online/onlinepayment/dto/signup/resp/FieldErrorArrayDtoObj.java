package uz.pdp.online.onlinepayment.dto.signup.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class FieldErrorArrayDtoObj {
    public Map<String, String> errors;
}
