package uz.pdp.online.onlinepayment.entity.inpostgres;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Region {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_id_gen")
    @SequenceGenerator(name = "region_id_gen", sequenceName = "region_id_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String name;

}