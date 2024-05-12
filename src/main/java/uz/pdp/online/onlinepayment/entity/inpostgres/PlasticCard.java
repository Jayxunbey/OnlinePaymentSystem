package uz.pdp.online.onlinepayment.entity.inpostgres;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "plastic_card")
public class PlasticCard {
    @Id
    @SequenceGenerator(name = "plastic_card_id_gen", sequenceName = "district_id_seq", allocationSize = 1)
    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "bank_account_number", nullable = false)
    private String bankAccountNumber;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Column(name = "issued_date", nullable = false)
    private LocalDate issuedDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "balance", nullable = false)
    private Double balance;

}