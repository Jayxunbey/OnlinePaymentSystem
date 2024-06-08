package uz.pdp.online.onlinepayment.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.onlinepayment.dto.transfer.TransferReqWithoutConfirmationDto;
import uz.pdp.online.onlinepayment.service.TransferService;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/api/transfer")
@RequiredArgsConstructor
public class TransfersController {


    private final TransferService transferService;

    @PostMapping("/transaction-via-without-confirmation")
    public void transaction(@RequestBody @Valid TransferReqWithoutConfirmationDto transferReqWithoutConfirmationDto) throws AccountNotFoundException {

        transferService.transaction(transferReqWithoutConfirmationDto);

        System.out.println("transferReqDto = " + transferReqWithoutConfirmationDto);

    }

    @PostMapping("/transaction-via-phone-confirmation")
    public void transactionWithConfirmation(@RequestBody @Valid TransferReqWithoutConfirmationDto transferReqWithoutConfirmationDto) throws AccountNotFoundException {

        transferService.transaction(transferReqWithoutConfirmationDto);

        System.out.println("transferReqDto = " + transferReqWithoutConfirmationDto);

    }

}
