package uz.pdp.online.onlinepayment.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.onlinepayment.dto.jay.signup.req.UserSignUpDto;
import uz.pdp.online.onlinepayment.dto.jay.signup.resp.RegistirationTempSentCodeRespDto;
import uz.pdp.online.onlinepayment.service.AuthService;

import javax.security.auth.login.AccountException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<RegistirationTempSentCodeRespDto> signUp(@RequestBody @Valid UserSignUpDto userSignUpDto) throws AccountException {

        RegistirationTempSentCodeRespDto auth = authService.auth(userSignUpDto);

        System.out.println("auth = " + auth);

        return ResponseEntity.ok(auth);

    }

}
