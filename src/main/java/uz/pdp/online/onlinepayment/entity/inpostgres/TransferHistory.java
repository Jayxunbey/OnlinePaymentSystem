package uz.pdp.online.onlinepayment.entity.inpostgres;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "transfer_history")
public class TransferHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfer_history_id_gen")
    @SequenceGenerator(name = "transfer_history_id_gen", sequenceName = "transfer_history_id_seq", allocationSize = 1)
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

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "fee", nullable = false)
    private BigDecimal fee;

    @Column(name = "sender_full_name", nullable = false)
    private String senderFullName;

    @Column(name = "recipient_full_name", nullable = false)
    private String recipientFullName;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

}