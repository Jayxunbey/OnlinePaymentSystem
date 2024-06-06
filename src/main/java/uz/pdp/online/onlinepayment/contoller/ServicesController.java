package uz.pdp.online.onlinepayment.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.dto.services.req.ChangeActiveReqDto;
import uz.pdp.online.onlinepayment.dto.services.resp.ServiceRespDto;
import uz.pdp.online.onlinepayment.dto.services.resp.ServiceRespForAllDto;
import uz.pdp.online.onlinepayment.dto.signup.req.ServicesAddingReqDto;
import uz.pdp.online.onlinepayment.dto.signup.req.ServicesUpdateReqDto;
import uz.pdp.online.onlinepayment.dto.signup.resp.FieldErrorArrayDtoObj;
import uz.pdp.online.onlinepayment.dto.signup.resp.MessageRespDtoObj;
import uz.pdp.online.onlinepayment.entity.inmongo.Service;
import uz.pdp.online.onlinepayment.service.ServicesService;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @Operation(
            tags = "Authentication",
            description = "Sign up confirmation place",
            responses = {
                    @ApiResponse(
                            description = "Added",
                            responseCode = "201",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"message\":\"Added Successfully\"\n}")}
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Not Acceptable",
                            responseCode = "406",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"error\":\"Field data is empty\"\n}")}
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"error\":\"Category not found\"\n}")}
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
    @PostMapping("/adding")
    public ResponseEntity<MessageRespDtoObj> addService(@RequestBody @Valid ServicesAddingReqDto servicesAddingReqDto) {

        servicesService.adding(servicesAddingReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageRespDtoObj("Added Succesfully"));
    }

    @Operation(
            tags = "Authentication",
            description = "Sign up confirmation place",
            responses = {@ApiResponse(
                    description = "Updated",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {@ExampleObject(value = "{\n     \t\"message\":\"Updated Successfully\"\n}")}
                            )
                    }
            ),
                    @ApiResponse(
                            description = "Not Acceptable",
                            responseCode = "406",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"error\":\"Field data is empty\"\n}")}
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"error\":\"Category not found\"\n}")}
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
    @PutMapping("/update")
    public ResponseEntity<MessageRespDtoObj> updateService(@RequestBody @Valid ServicesUpdateReqDto servicesUpdateReqDto) {

        servicesService.update(servicesUpdateReqDto);

        return ResponseEntity.ok().body(new MessageRespDtoObj("Updated Succesfully"));
    }

    @Operation(
            tags = "Authentication",
            description = "Sign up confirmation place",
            responses = {
                    @ApiResponse(
                            description = "Changed",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"message\":\"Changed Successfully\"\n}")}
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {@ExampleObject(value = "{\n     \t\"error\":\"Service not found\"\n}")}
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
    @PutMapping("/change-active")
    public ResponseEntity<MessageRespDtoObj> changeActiveByServiceNumber(@RequestBody @Valid ChangeActiveReqDto changeActiveReqDto) {
        servicesService.changeActive(changeActiveReqDto);
        return ResponseEntity.ok().body(new MessageRespDtoObj("Changed Successfully"));
    }

    @Operation(
            tags = "Authentication",
            description = "Sign up confirmation place",
            responses = {
                    @ApiResponse(
                            description = "Lists",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(arraySchema = @Schema(implementation = ServiceRespDto.class))
                                    )
                            }
                    )
            }
    )
    @GetMapping("/category-number/all/{number}")
    public ResponseEntity<List<ServiceRespForAllDto>> getServicesAllByCategory(@PathVariable String number) {

        List<ServiceRespForAllDto> allByCategoryNumberAll = servicesService.getAllByCategoryNumberAll(number);

        return ResponseEntity.ok().body(allByCategoryNumberAll);

    }

    @DeleteMapping("/delete/number/{number}")
    public void deleteService(@PathVariable String number) {

        servicesService.deleteService(number);

    }

    @GetMapping("/category-number/{number}")
    public ResponseEntity<List<Service>> getServicesByCategory(@PathVariable String number) {

        List<Service> allByCategoryNumber = servicesService.getAllByCategoryNumberAndActiveTrue(number);

        return ResponseEntity.ok().body(allByCategoryNumber);
    }

}
