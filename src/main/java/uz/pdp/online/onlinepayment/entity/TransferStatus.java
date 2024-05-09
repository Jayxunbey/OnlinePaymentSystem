package uz.pdp.online.onlinepayment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transfer_status")
public class TransferStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfer_status_id_gen")
    @SequenceGenerator(name = "transfer_status_id_gen", sequenceName = "transfer_status_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fee", nullable = false)
    private Double fee;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

}