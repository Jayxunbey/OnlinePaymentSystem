package uz.pdp.online.onlinepayment.dto.transfer;

import jakarta.validation.constraints.Pattern;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class TransferReqWithoutConfirmationDto {

    @Pattern(message = "plastic card number is not valid", regexp = "[0-9]{16}")
    String from;

    @Pattern(message = "plastic card number is not valid", regexp = "[0-9]{16}")
    String to;

    BigDecimal amount;

}
