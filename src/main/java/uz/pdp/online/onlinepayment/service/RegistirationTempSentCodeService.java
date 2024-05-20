package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.dto.signup.req.UserSignUpDto;
import uz.pdp.online.onlinepayment.dto.signup.resp.RegistirationTempSentCodeRespDto;
import uz.pdp.online.onlinepayment.entity.inpostgres.RegistirationTempSentCode;
import uz.pdp.online.onlinepayment.jwt.JwtProvider;
import uz.pdp.online.onlinepayment.repo.inpostgres.RegistirationTempSentCodeRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RegistirationTempSentCodeService {

    private final SendSmsService sendSmsService;
    private final RegistirationTempSentCodeRepository registirationTempSentCodeRepository;
    private final Random random;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.security.jwt.sending-sms-code-time-minute}")
    private int expirationSendingSmsTimeMinute;

    public RegistirationTempSentCodeRespDto sendCodeTo(UserSignUpDto userSignUpDto) {

        String randomCode = generateRandomCode(5);
        String message = "Your verification code: " + randomCode;

        sendSmsService.sendCodeTo(userSignUpDto.getPhone(), message);

        Map<String, String> claims = new HashMap<>();
        claims.put("phone", userSignUpDto.getPhone());
        claims.put("name",userSignUpDto.getName());

        String token = jwtProvider.generateTokenForSendingSms(claims, randomCode, expirationSendingSmsTimeMinute);

        RegistirationTempSentCode registirationTempSentCode =
                new RegistirationTempSentCode(
                        token,
                        passwordEncoder.encode(randomCode), new Date(System.currentTimeMillis() + (long) expirationSendingSmsTimeMinute * 60 * 1000),
                        userSignUpDto.getPhone(),
                        userSignUpDto.getName(),
                        userSignUpDto.getNew_password());

        registirationTempSentCodeRepository.save(registirationTempSentCode);

        return new RegistirationTempSentCodeRespDto(token, expirationSendingSmsTimeMinute);


    }

    private String generateRandomCode(int numberLength) {
        int randomNumber = random.nextInt((int) Math.pow(10, numberLength - 1), (int) Math.pow(10, numberLength));
        return String.valueOf(randomNumber);
    }


    public void deleteAllWhichBefore(Date date) {
        registirationTempSentCodeRepository.deleteByExpirationBefore(date);
    }

    public Optional<RegistirationTempSentCode> getObjectBy(String token) {
        return registirationTempSentCodeRepository.findByToken(token);
    }

    public boolean isMatchEncodeAndDecode(String rawPassword, String encodedPassword) {

        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("matches["+rawPassword+", "+encodedPassword+"] = " + matches);
        return matches;
    }
}
