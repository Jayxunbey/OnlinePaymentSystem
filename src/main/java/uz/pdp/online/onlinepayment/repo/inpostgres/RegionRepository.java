package uz.pdp.online.onlinepayment.repo.inpostgres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.online.onlinepayment.entity.inpostgres.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    boolean existsByName(String name);
}
