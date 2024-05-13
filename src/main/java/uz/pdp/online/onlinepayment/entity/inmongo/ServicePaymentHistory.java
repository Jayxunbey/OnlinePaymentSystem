package uz.pdp.online.onlinepayment.entity.inmongo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "service_payment_history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ServicePaymentHistory {

    @Id
    private String id;

    @Field(name = "user_id")
    private String userId;

    private String number;

    @Field(name = "field_history")
    private FieldHistory[] fieldHistory;

    @Field(name = "service_number")
    private String serviceNumber;

    @Field(name = "created_date")
    private Date createdDate;

    @Field(name = "payment_date")
    private Date paymentDate;

    private Double amount;

    private Double fee;

    private Double cashback;

    private String status;

    private String debit_card;


}
