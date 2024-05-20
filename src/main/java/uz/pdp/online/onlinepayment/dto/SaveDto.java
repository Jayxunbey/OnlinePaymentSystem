package uz.pdp.online.onlinepayment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveDto {
    private Long id;
    private String number;
    private String name;
    private String type;
    private boolean active;
}
