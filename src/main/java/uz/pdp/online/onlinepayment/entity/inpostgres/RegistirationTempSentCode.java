package uz.pdp.online.onlinepayment.entity.inpostgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "registiration_temp_sent_code")
public class RegistirationTempSentCode {
    @Id
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "sent_code", nullable = false)
    private String sentCode;

    @Column(name = "expiration", nullable = false)
    private Date expiration;

    public RegistirationTempSentCode(String token, String sentCode, Date expiration) {
        this.token = token;
        this.sentCode = sentCode;
        this.expiration = expiration;
    }

    public RegistirationTempSentCode() {
    }
}