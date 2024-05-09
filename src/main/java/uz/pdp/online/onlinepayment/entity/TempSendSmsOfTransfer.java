package uz.pdp.online.onlinepayment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "temp_send_sms_of_transfer")
public class TempSendSmsOfTransfer {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "debit_card", nullable = false)
    private PlasticCard debitCard;

    @Column(name = "recipient_card", nullable = false)
    private String recipientCard;

    @Column(name = "recipient_full_name", nullable = false)
    private String recipientFullName;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "expiration", nullable = false)
    private LocalDate expiration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transfer_status", nullable = false)
    private TransferStatus transferStatus;

    @Column(name = "sent_code", nullable = false)
    private String sentCode;

}