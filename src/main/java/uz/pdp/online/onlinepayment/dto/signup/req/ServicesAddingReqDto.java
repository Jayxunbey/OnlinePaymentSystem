package uz.pdp.online.onlinepayment.dto.signup.req;

import lombok.Value;
import uz.pdp.online.onlinepayment.entity.inmongo.Field;

import java.util.List;

@Value
public class ServicesAddingReqDto {
    String name;
    List<Field> fields;
}
