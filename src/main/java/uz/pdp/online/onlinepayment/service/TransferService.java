package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.Transfers.NotEnoughMoneyAtBalanceException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardNotFoundException;
import uz.pdp.online.onlinepayment.dto.transfer.TransferReqWithoutConfirmationDto;
import uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransferService {

    private final CentralBankServices centralBankServices;
    private final PlasticCardService plasticCardService;

    public void transaction(TransferReqWithoutConfirmationDto transferReqWithoutConfirmationDto) {

        Optional<PlasticCard> optionalPlasticCard = plasticCardService.getPlasticCardViaNumber(transferReqWithoutConfirmationDto.getFrom());

        if (optionalPlasticCard.isEmpty()) {
            throw new PlasticCardNotFoundException();
        }

        PlasticCard plasticCard = optionalPlasticCard.get();

        plasticCardIsBelongsToCurrentlyUser(transferReqWithoutConfirmationDto.getFrom(), plasticCard);

        openTransactionAndTransfer(plasticCard, transferReqWithoutConfirmationDto.getTo(), transferReqWithoutConfirmationDto.getAmount());

    }

    private void openTransactionAndTransfer(PlasticCard fromPlasticCard, String to, BigDecimal amount) {

        BigDecimal fee = BigDecimal.valueOf(0.25);

        BigDecimal feeAmount = amount.multiply(fee);

        BigDecimal totalAmount = amount.add(feeAmount);

        if (fromPlasticCard.getBalance().compareTo(amount)<0) {
            throw new NotEnoughMoneyAtBalanceException();
        }

        centralBankServices.transferMoney(fromPlasticCard,to, totalAmount);

    }

    private void plasticCardIsBelongsToCurrentlyUser(String from, PlasticCard plasticCard) {

        String phone = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!phone.equals(plasticCard.getPhoneNumber()) || !plasticCard.getNumber().equals(from) || !plasticCard.getActive()) {
            throw new PlasticCardNotFoundException();
        }

    }
}
