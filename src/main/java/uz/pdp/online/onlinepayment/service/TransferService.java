package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.Transfers.NotEnoughMoneyAtBalanceException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.plasticcard.PlasticCardNotFoundException;
import uz.pdp.online.onlinepayment.dto.betweens.PlasticCardDetailsDto;
import uz.pdp.online.onlinepayment.dto.transfer.TransferReqWithoutConfirmationDto;
import uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransferService {

    private static final Logger log = LoggerFactory.getLogger(TransferService.class);
    private final CentralBankServices centralBankServices;
    private final PlasticCardService plasticCardService;
    private final TransferHistoryService transferHistoryService;

    public void transaction(TransferReqWithoutConfirmationDto transferReqWithoutConfirmationDto) throws AccountNotFoundException {

        Optional<PlasticCard> optionalPlasticCard = plasticCardService.getPlasticCardViaNumber(transferReqWithoutConfirmationDto.getFrom());

        if (optionalPlasticCard.isEmpty()) {
            throw new PlasticCardNotFoundException();
        }

        PlasticCard plasticCard = optionalPlasticCard.get();

        plasticCardIsBelongsToCurrentlyUser(transferReqWithoutConfirmationDto.getFrom(), plasticCard);

        openTransactionAndTransfer(plasticCard, transferReqWithoutConfirmationDto.getTo(), transferReqWithoutConfirmationDto.getAmount());

    }

    private void openTransactionAndTransfer(PlasticCard fromPlasticCard, String to, BigDecimal amount) throws AccountNotFoundException {

        Date transactionOpenedDate = new Date();

        BigDecimal feePercent = BigDecimal.valueOf(0.25);

        BigDecimal feeAmount = amount.divide(BigDecimal.valueOf(100)).multiply(feePercent);

        log.info("Amount to be transferred: " + amount);
        log.info("Transfer feePercent " + feePercent);
        log.info("Transfer feeAmount " + feeAmount);

        if (fromPlasticCard.getBalance().compareTo(amount.add(feeAmount))<0) {
            throw new NotEnoughMoneyAtBalanceException();
        }

        PlasticCardDetailsDto toPlasticCard = centralBankServices.getPlasticCardWithNumber(to);

        if (centralBankServices.transferMoney(fromPlasticCard,toPlasticCard, amount, feeAmount)) {
            saveTransferHistory(fromPlasticCard,toPlasticCard, feeAmount, amount, transactionOpenedDate, new Date());
        }


    }

    private void saveTransferHistory(PlasticCard fromPlasticCard, PlasticCardDetailsDto toPlasticCard, BigDecimal feeAmount, BigDecimal amount, Date createdDate, Date paymentDate) throws AccountNotFoundException {
        transferHistoryService.saveToDatabase(fromPlasticCard,
                toPlasticCard.getNumber(), feeAmount,amount, toPlasticCard.getOwnerName(), createdDate, paymentDate);
    }

    private void plasticCardIsBelongsToCurrentlyUser(String from, PlasticCard plasticCard) {

        String phone = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!phone.equals(plasticCard.getPhoneNumber()) || !plasticCard.getNumber().equals(from) || !plasticCard.getActive()) {
            throw new PlasticCardNotFoundException();
        }

    }
}
