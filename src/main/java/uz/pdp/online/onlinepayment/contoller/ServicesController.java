package uz.pdp.online.onlinepayment.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.dto.services.req.ChangeActiveReqDto;
import uz.pdp.online.onlinepayment.dto.signup.req.ServicesAddingReqDto;
import uz.pdp.online.onlinepayment.dto.signup.req.ServicesUpdateReqDto;
import uz.pdp.online.onlinepayment.dto.signup.resp.MessageRespDtoObj;
import uz.pdp.online.onlinepayment.entity.inmongo.Service;
import uz.pdp.online.onlinepayment.service.ServicesService;

import java.util.List;

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

    @PutMapping("/update")
    public ResponseEntity<MessageRespDtoObj> updateService(@RequestBody @Valid ServicesUpdateReqDto servicesUpdateReqDto) {

        servicesService.update(servicesUpdateReqDto);

        return ResponseEntity.ok().body(new MessageRespDtoObj("Updated Succesfully"));
    }

    @PutMapping("/change-active")
    public ResponseEntity<MessageRespDtoObj> changeActiveByServiceNumber(@RequestBody @Valid ChangeActiveReqDto changeActiveReqDto) {
        servicesService.changeActive(changeActiveReqDto);
        return ResponseEntity.ok().body(new MessageRespDtoObj("Changed Successfully"));
    }

    @GetMapping("/category-number/all/{number}")
    public ResponseEntity<List<Service>> getServicesAllByCategory(@PathVariable String number) {

        List<Service> allByCategoryNumber = servicesService.getAllByCategoryNumberAll(number);

        return ResponseEntity.ok().body(allByCategoryNumber);
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
