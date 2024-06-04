package uz.pdp.online.onlinepayment.entity.inmongo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Service {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    private String categoryId;

    private Integer number;

    @Field(name = "request_address")
    private String requestAddress;

    private uz.pdp.online.onlinepayment.entity.inmongo.Field[] fields;

    private BigDecimal fee;

    private BigDecimal cashback;

    private boolean active;

}
