package uz.pdp.online.onlinepayment.entity.inpostgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "registiration_temp_sent_code")
@AllArgsConstructor
@NoArgsConstructor
public class RegistirationTempSentCode {
    @Id
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "sent_code", nullable = false)
    private String sentCode;

    @Column(name = "expiration", nullable = false)
    private Date expiration;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;


}