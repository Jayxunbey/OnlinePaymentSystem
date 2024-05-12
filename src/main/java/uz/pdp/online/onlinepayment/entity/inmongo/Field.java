package uz.pdp.online.onlinepayment.entity.inmongo;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Field {
    private String name;
    private String type;
    private boolean required;
}
