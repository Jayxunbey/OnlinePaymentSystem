package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard;
import uz.pdp.online.onlinepayment.entity.inpostgres.TransferHistory;
import uz.pdp.online.onlinepayment.repo.inpostgres.TransferHistoryRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Date;

@Service
//@Transactional
@RequiredArgsConstructor
public class TransferHistoryService {

    private final TransferHistoryRepository transferHistoryRepository;
    private final UserService userService;

    public void saveToDatabase(PlasticCard fromPlasticCard, String recipientPlasticNumber, BigDecimal feeAmount, BigDecimal amount, String recipientOwnerFullName, Date createdDate, Date paymentDate) throws AccountNotFoundException {
        TransferHistory transferHistory = new TransferHistory();
        transferHistory.setUser(userService.getUserByPhoneNumber(fromPlasticCard.getPhoneNumber()));
        transferHistory.setFee(feeAmount);
        transferHistory.setSenderFullName(fromPlasticCard.getOwnerName());
        transferHistory.setPaymentDate(paymentDate);
        transferHistory.setCreatedDate(createdDate);
        transferHistory.setRecipientFullName(recipientOwnerFullName);
        transferHistory.setDebitCard(fromPlasticCard);
        transferHistory.setAmount(amount);
        transferHistory.setRecipientCard(recipientPlasticNumber);
        transferHistoryRepository.save(transferHistory);
    }
}
