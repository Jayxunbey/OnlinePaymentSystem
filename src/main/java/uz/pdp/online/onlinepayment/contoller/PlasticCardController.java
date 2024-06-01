package uz.pdp.online.onlinepayment.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.dto.signup.req.PlasticCardAddReqDto;
import uz.pdp.online.onlinepayment.dto.signup.req.PlasticCardReqUpdateDto;
import uz.pdp.online.onlinepayment.dto.signup.resp.FieldErrorArrayDtoObj;
import uz.pdp.online.onlinepayment.dto.signup.resp.MessageRespDtoObj;
import uz.pdp.online.onlinepayment.dto.signup.resp.PlasticCardResponseDto;
import uz.pdp.online.onlinepayment.service.PlasticCardService;

import javax.security.auth.login.AccountNotFoundException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/plastic-card")
@RequiredArgsConstructor
@Tag(name = "Plastic Card", description = "To use all requests on this application you must authorize via Admin and User")
public class PlasticCardController {

    private final PlasticCardService plasticCardService;


    @Operation(
            description = "To use this method you must have User Role only",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"message\":\"Added Successfully\"\n}")}
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Plastic card already exists",
                            responseCode = "406",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"error\":\"error message\"\n}")}
                                    )
                            }

                    ),
                    @ApiResponse(
                            description = "Plastic Card not found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"error\":\"error message\"\n}")}
                                    )
                            }

                    ),
                    @ApiResponse(
                            description = "Fields[] validating errors",
                            responseCode = "406",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = FieldErrorArrayDtoObj.class)
                                    )
                            }
                    )
            }
    )
    @Secured("ROLE_USER")
    @PostMapping("/adding")
    public ResponseEntity<MessageRespDtoObj> addPlasticCard(@RequestBody @Valid PlasticCardAddReqDto plasticCardAddReqDto) throws ParseException, AccountNotFoundException {
        plasticCardService.addPlasticCard(plasticCardAddReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageRespDtoObj("Added successfully"));

    }

    @Operation(
            description = "To use this method you must have User Role only",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PlasticCardResponseDto.class))
                                    )
                            }
                    )
            }
    )
    @GetMapping("/all")
    @Secured("ROLE_USER")
    public ResponseEntity<List<PlasticCardResponseDto>> allPlasticCard() {

        List<PlasticCardResponseDto> allPlasticCard = plasticCardService.getAllPlasticCardForResp();

        return ResponseEntity.ok(allPlasticCard);

    }

    @Operation(
            description = "To use this method you must have User Role only",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "201",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = PlasticCardResponseDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Plastic Card not found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"error\":\"error message\"\n}")}
                                    )
                            }

                    ),
                    @ApiResponse(
                            description = "Fields[] validating errors",
                            responseCode = "406",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = FieldErrorArrayDtoObj.class)
                                    )
                            }
                    )
            }
    )
    @Secured("ROLE_USER")
    @PutMapping("/update/name")
    public ResponseEntity<PlasticCardResponseDto> updatePlasticCard(@RequestBody @Valid PlasticCardReqUpdateDto plasticCardReqUpdateDto) {

        PlasticCardResponseDto plasticCardResponseDto = plasticCardService.updateName(plasticCardReqUpdateDto);

        return ResponseEntity.ok().body(plasticCardResponseDto);

    }

    @Operation(
            description = "To use this method you must have User Role only",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200",
                            content = @Content(schema = @Schema())
                    ),
                    @ApiResponse(
                            description = "Plastic Card not found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"error\":\"error message\"\n}")}
                                    )
                            }

                    )
            }
    )
    @Secured("ROLE_USER")
    @DeleteMapping("/delete/{number}")
    public void deletePlasticCard(@PathVariable("number") String number) {

        plasticCardService.deleteCard(number);

    }

}
