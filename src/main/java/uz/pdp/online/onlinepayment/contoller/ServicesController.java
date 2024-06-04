package uz.pdp.online.onlinepayment.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.onlinepayment.dto.signup.req.ServicesAddingReqDto;
import uz.pdp.online.onlinepayment.dto.signup.resp.MessageRespDtoObj;
import uz.pdp.online.onlinepayment.service.ServicesService;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping("/adding")
    public ResponseEntity<MessageRespDtoObj> addService(@RequestBody @Valid ServicesAddingReqDto servicesAddingReqDto) {

        servicesService.adding(servicesAddingReqDto);

        return ResponseEntity.ok().body(new MessageRespDtoObj("Added Succesfully"));

    }

}
