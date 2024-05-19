package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.dto.jay.signup.resp.RegistirationTempSentCodeRespDto;
import uz.pdp.online.onlinepayment.entity.inpostgres.RegistirationTempSentCode;
import uz.pdp.online.onlinepayment.jwt.JwtProvider;
import uz.pdp.online.onlinepayment.repo.inpostgres.RegistirationTempSentCodeRepository;

import java.util.Date;
import java.util.Random;

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

    public RegistirationTempSentCodeRespDto sendCodeTo(String phone) {

        String randomCode = generateRandomCode(5);
        String message = "Your verification code: " + randomCode;

        sendSmsService.sendCodeTo(phone, message);

        String token = jwtProvider.generateTokenForSendingSms(phone, randomCode, expirationSendingSmsTimeMinute);

        RegistirationTempSentCode registirationTempSentCode = new RegistirationTempSentCode(token, passwordEncoder.encode(randomCode), new Date(System.currentTimeMillis() + (long) expirationSendingSmsTimeMinute * 60 * 1000));

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
}
