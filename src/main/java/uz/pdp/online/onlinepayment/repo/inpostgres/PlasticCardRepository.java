package uz.pdp.online.onlinepayment.repo.inpostgres;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.onlinepayment.entity.inpostgres.PlasticCard;

import java.util.List;
import java.util.Optional;

public interface PlasticCardRepository extends JpaRepository<PlasticCard, String> {

    Optional<PlasticCard> findByNumber(String number);

    List<PlasticCard> findByPhoneNumber(String phoneNumber);
}