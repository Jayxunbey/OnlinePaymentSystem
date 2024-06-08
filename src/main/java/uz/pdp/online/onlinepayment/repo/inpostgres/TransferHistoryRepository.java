package uz.pdp.online.onlinepayment.repo.inpostgres;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.onlinepayment.entity.inpostgres.TransferHistory;

public interface TransferHistoryRepository extends JpaRepository<TransferHistory, Long> {
}