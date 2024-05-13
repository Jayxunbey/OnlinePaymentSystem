package uz.pdp.online.onlinepayment.entity.inmongo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "confirm_payment_for_service")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConfirmPaymentForService {

    @Id
    private String id;

    @org.springframework.data.mongodb.core.mapping.Field(name = "user_id")
    private String userId;

    private String token;

    @Field(name = "sent_code")
    private String sentCode;

    @org.springframework.data.mongodb.core.mapping.Field(name = "field_history")
    private FieldHistory[] fieldHistory;

    @org.springframework.data.mongodb.core.mapping.Field(name = "service_number")
    private String serviceNumber;

    @org.springframework.data.mongodb.core.mapping.Field(name = "created_date")
    private Date createdDate;

    private Double amount;

    private Double fee;

    private Double cashback;

    private String status;

    private String debit_card;

    private Date expiration;

}
