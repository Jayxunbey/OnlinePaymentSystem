package uz.pdp.online.onlinepayment.repo.inpostgres;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.onlinepayment.entity.inpostgres.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByPhone(String phone);
}