package uz.pdp.online.onlinepayment.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.onlinepayment.dto.signup.req.ServicesAddingReqDto;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

    @PostMapping("/adding")
    public ResponseEntity<ServicesAddingReqDto> addService(@RequestBody ServicesAddingReqDto servicesAddingReqDto) {

        System.out.println("servicesAddingReqDto.toString() = " + servicesAddingReqDto.toString());

        return ResponseEntity.ok().body(servicesAddingReqDto);

    }

}
