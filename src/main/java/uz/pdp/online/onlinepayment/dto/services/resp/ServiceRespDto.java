package uz.pdp.online.onlinepayment.dto.services.resp;

import lombok.Value;
import uz.pdp.online.onlinepayment.entity.inmongo.Field;

import java.math.BigDecimal;

@Value
public class ServiceRespDto {

    private String name;

    private String categoryId;

    private Integer number;

    private String requestAddress;

    private Field[] fields;

    private BigDecimal fee;

    private BigDecimal cashback;

    private boolean active;

}
