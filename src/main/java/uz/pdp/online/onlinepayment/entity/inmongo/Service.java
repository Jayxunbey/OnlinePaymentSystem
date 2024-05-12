package uz.pdp.online.onlinepayment.entity.inmongo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

    @Field(name = "service_number")
    private String serviceNumber;

    @Field(name = "request_address")
    private String requestAddress;

    private uz.pdp.online.onlinepayment.entity.inmongo.Field[] fields;

    private Double fee;

    private Double cashback;

    private boolean active;

    private boolean available;

}
