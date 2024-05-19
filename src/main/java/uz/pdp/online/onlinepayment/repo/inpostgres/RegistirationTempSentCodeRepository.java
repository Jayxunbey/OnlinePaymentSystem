package uz.pdp.online.onlinepayment.repo.inpostgres;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.onlinepayment.entity.inpostgres.RegistirationTempSentCode;

import java.util.Date;
import java.util.Optional;

public interface RegistirationTempSentCodeRepository extends JpaRepository<RegistirationTempSentCode, Long> {

    void deleteByExpirationBefore(Date expiration);

    Optional<RegistirationTempSentCode> findByToken(String token);
}