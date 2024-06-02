package uz.pdp.online.onlinepayment.entity.inmongo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Category entity representing a category in the system")
public class Category {

    @Id
    @Schema(description = "Unique identifier of the category", example = "60b8d2955edebf001f4d75c6")
    private String id;

    @Schema(description = "Number of the category")
    private String number;

    @Schema(description = "Name of the category")
    private String name;

    @Schema(description = "Type of the category")
    private String type;

    @Schema(description = "Indicates if the category is active", example = "true")
    private boolean active;
}
