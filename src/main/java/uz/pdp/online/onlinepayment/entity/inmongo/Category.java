package uz.pdp.online.onlinepayment.entity.inmongo;


import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    @Id
    private String id;
    private String number;
    private String name;
    private String type;
    private boolean active;

}
