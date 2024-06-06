package uz.pdp.online.onlinepayment.entity.inmongo;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Field {

    private List<FieldCaptionsOnLanguages> fieldCaptionsOnLanguages;
    private String name;
    private String type;
    private boolean required;
}
