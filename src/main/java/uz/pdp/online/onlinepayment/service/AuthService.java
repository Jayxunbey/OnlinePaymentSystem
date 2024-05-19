package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.dto.jay.signup.req.UserSignUpDto;
import uz.pdp.online.onlinepayment.dto.jay.signup.resp.RegistirationTempSentCodeRespDto;

import javax.security.auth.login.AccountException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final RegistirationTempSentCodeService registirationTempSentCodeService;

    public RegistirationTempSentCodeRespDto auth(UserSignUpDto userSignUpDto) throws AccountException {
        String phone = userSignUpDto.getPhone();
        if (userService.isAvailable(phone)) {
            throw new AccountException("Account already exists");
        }

        return registirationTempSentCodeService.sendCodeTo(phone);



    }

}
