package uz.pdp.online.onlinepayment.entity.inmongo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FieldHistory {
    private String name;
    private String value;
    private String type;
}
