package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.dto.signup.req.UserLoginDto;
import uz.pdp.online.onlinepayment.dto.signup.req.UserSignUpConfirmDto;
import uz.pdp.online.onlinepayment.dto.signup.req.UserSignUpDto;
import uz.pdp.online.onlinepayment.dto.signup.resp.RegistirationTempSentCodeRespDto;
import uz.pdp.online.onlinepayment.entity.inpostgres.RegistirationTempSentCode;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final RegistirationTempSentCodeService registirationTempSentCodeService;

    public RegistirationTempSentCodeRespDto auth(UserSignUpDto userSignUpDto) throws AccountException {
        String phone = userSignUpDto.getPhone();

        if (!userSignUpDto.getNew_password().equals(userSignUpDto.getRepeat_password())) {
            throw new AccountException("Passwords do not match");
        }

        if (userService.isAvailable(phone)) {
            throw new AccountException("Account already exists");
        }

        return registirationTempSentCodeService.sendCodeTo(userSignUpDto);


    }

    public String confirm(UserSignUpConfirmDto signUpConfirmDto) throws AccountException {
        String token = signUpConfirmDto.getToken();
        var objectBy = registirationTempSentCodeService.getObjectBy(token);
        if (objectBy.isEmpty()) {
            throw new AccountException("try again");
        }

        RegistirationTempSentCode registirationTempSentCode = objectBy.get();

        if (!registirationTempSentCodeService.isMatchEncodeAndDecode(signUpConfirmDto.getConfirmation_code(), registirationTempSentCode.getSentCode())) {
            throw new AccountException("confirmation code is different");
        }

        return userService.registerUser(registirationTempSentCode);


    }

    public String login(UserLoginDto userLoginDto) throws AccountNotFoundException {
        return userService.login(userLoginDto);
    }
}
