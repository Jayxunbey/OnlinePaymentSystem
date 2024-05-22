package uz.pdp.online.onlinepayment.dto.signup.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateDto {
    private String name;
    private String type;
    private boolean active;
}

