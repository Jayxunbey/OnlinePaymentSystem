package uz.pdp.online.onlinepayment.dto.services.resp;

import lombok.Value;
import uz.pdp.online.onlinepayment.entity.inmongo.Field;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link uz.pdp.online.onlinepayment.entity.inmongo.Service}
 */
@Value
public class ServiceRespForAllDto implements Serializable {
    String name;
    String categoryId;
    Integer number;
    Field[] fields;
    String requestAddress;
    BigDecimal fee;
    BigDecimal cashback;
}