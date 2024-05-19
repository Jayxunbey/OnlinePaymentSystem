package uz.pdp.online.onlinepayment.repo.inpostgres;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.onlinepayment.entity.inpostgres.RegistirationTempSentCode;

import java.util.Date;

public interface RegistirationTempSentCodeRepository extends JpaRepository<RegistirationTempSentCode, Long> {

    void deleteByExpirationBefore(Date expiration);
}