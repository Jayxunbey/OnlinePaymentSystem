package uz.pdp.online.onlinepayment.repo.inpostgres;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.onlinepayment.entity.inpostgres.RegistirationTempSentCode;

public interface RegistirationTempSentCodeRepository extends JpaRepository<RegistirationTempSentCode, Long> {

}