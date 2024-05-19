package uz.pdp.online.onlinepayment.entity.inpostgres;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "person_identify")
public class PersonIdentify {
    @Id
    @SequenceGenerator(name = "person_identify_id_gen",sequenceName = "district_id_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;
    @Column(name = "id_card", nullable = false)
    private String idCard;
    @Column(name = "issued_date", nullable = false)
    private LocalDate issuedDate;
    @Column(name = "expiration", nullable = false)
    private LocalDate expiration;
    @Column(name = "jshshir", nullable = false)
    private String jshshir;
    @Column(name = "address", nullable = false)
    private String address;
}