package uz.pdp.online.onlinepayment.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.onlinepayment.dto.signup.req.UserLoginDto;
import uz.pdp.online.onlinepayment.dto.signup.req.UserSignUpConfirmDto;
import uz.pdp.online.onlinepayment.dto.signup.req.UserSignUpDto;
import uz.pdp.online.onlinepayment.dto.signup.resp.RegistirationTempSentCodeRespDto;
import uz.pdp.online.onlinepayment.service.AuthService;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;

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

    @PostMapping("/sign-up-confirm")
    public ResponseEntity<String> signUpConfirm(@RequestBody @Valid UserSignUpConfirmDto signUpConfirmDto) throws AccountException {
        String authToken = authService.confirm(signUpConfirmDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .body("Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDto userLoginDto) throws AccountNotFoundException {

        String authToken = authService.login(userLoginDto);

        return ResponseEntity
                .accepted()
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .body("Login Success");
    }
}
