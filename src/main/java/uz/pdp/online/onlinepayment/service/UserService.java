package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.onlinepayment.dto.signup.req.UserLoginDto;
import uz.pdp.online.onlinepayment.entity.inpostgres.RegistirationTempSentCode;
import uz.pdp.online.onlinepayment.entity.inpostgres.User;
import uz.pdp.online.onlinepayment.jwt.JwtProvider;
import uz.pdp.online.onlinepayment.repo.inpostgres.UserRepository;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public boolean isAvailable(String phone) {

        return userRepository.findByPhone(phone).isPresent();

    }

    public String registerUser(RegistirationTempSentCode registirationTempSentCode) throws AccountException {


        if (isAvailable(registirationTempSentCode.getPhone())) {
            throw new AccountException("Account already exists");
        }

        User user = new User();
        String encode = passwordEncoder.encode(registirationTempSentCode.getPassword());

        user.setId(UUID.randomUUID().toString());
        user.setPhone(registirationTempSentCode.getPhone());
        user.setPassword(encode);
        user.setName(registirationTempSentCode.getName());
        user.setIdentify(false);
        user.setActive(true);
        user.setRole("ROLE_USER");

        userRepository.save(user);

        String token = jwtProvider.generateToken(
                registirationTempSentCode.getPhone(),
                Map.of("role", user.getRole()),
                false);

        return jwtProvider.addBearerToken(token);
    }

    public String login(UserLoginDto userLoginDto) throws AccountNotFoundException {
        String phone = userLoginDto.getPhone();
        String password = userLoginDto.getPassword();

        Optional<User> byPhone = userRepository.findByPhone(phone);

        if (byPhone.isEmpty()) {
            throw new AccountNotFoundException("User not found");
        }
        User user = byPhone.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AccountNotFoundException("phone or password wrong");
        }

        return jwtProvider.addBearerToken(
                jwtProvider.generateToken(
                        user.getPhone(),
                        Map.of("role", user.getRole()),
                        userLoginDto.isRememberMe()
                )
        );
    }
}
