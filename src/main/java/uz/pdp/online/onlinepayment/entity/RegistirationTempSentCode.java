package uz.pdp.online.onlinepayment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "registiration_temp_sent_code")
public class RegistirationTempSentCode {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "sent_code", nullable = false)
    private String sentCode;

    @Column(name = "expiration", nullable = false)
    private LocalDate expiration;

}