package uz.pdp.online.onlinepayment.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@Tag(name = "Authentication",description = "To use all requests on this application you must authorize")
public class AuthController {

    private final AuthService authService;

    @Operation(
            responses = {@ApiResponse(
                    description = "Accepted and see other",
                    responseCode = "303"
                    ),
                    @ApiResponse(
                            description = "User already exists",
                            responseCode = "400",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
//                                            schema = @Schema(implementation = ErrorDtoObj.class),
                                            examples = {@ExampleObject(value = "{\n\t\"error\":\"error message\"\n}")}
//                                            array = @ArraySchema(schema = @Schema(implementation = ErrorDtoObj.class))
                                           )
                            }

                    ),
                    @ApiResponse(
                            description = "Fields[] validating errors",
                            responseCode = "406"
                    )
            }
    )
    @PostMapping(value = "/sign-up", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RegistirationTempSentCodeRespDto> signUp(@RequestBody @Valid UserSignUpDto userSignUpDto) throws AccountException {

        RegistirationTempSentCodeRespDto auth = authService.auth(userSignUpDto);

        System.out.println("auth = " + auth);

        return ResponseEntity.status(HttpStatus.SEE_OTHER).body(auth);

    }

    @Operation(
            tags = "Authentication",
            description = "Sign up confirmation place",
            responses = {@ApiResponse(
                    description = "Created",
                    responseCode = "201"
            ),
                    @ApiResponse(
                            description = "Not Acceptable",
                            responseCode = "406"
                    ),
                    @ApiResponse(
                            description = "Fields[] validating errors",
                            responseCode = "406"
                    )
            }
    )

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
