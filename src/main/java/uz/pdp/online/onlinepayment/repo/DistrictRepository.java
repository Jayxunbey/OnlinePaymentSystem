package uz.pdp.online.onlinepayment.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.online.onlinepayment.entity.inpostgres.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    boolean existsByName(String name);
}
