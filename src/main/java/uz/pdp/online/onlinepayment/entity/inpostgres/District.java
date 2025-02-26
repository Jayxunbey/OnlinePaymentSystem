package uz.pdp.online.onlinepayment.entity.inpostgres;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_id_gen")
    @SequenceGenerator(name = "district_id_gen", sequenceName = "district_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
}
