package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.entity.inpostgres.User;
import uz.pdp.online.onlinepayment.repo.inpostgres.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean isAvailable(String phone) {

        return userRepository.findByPhone(phone).isPresent();

    }
}
