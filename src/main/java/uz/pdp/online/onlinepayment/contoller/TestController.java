package uz.pdp.online.onlinepayment.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.onlinepayment.entity.inmongo.Field;
import uz.pdp.online.onlinepayment.entity.inmongo.Service;
import uz.pdp.online.onlinepayment.repo.inmongo.ServiceRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {

    private final ServiceRepository serviceRepository;

    @GetMapping("/get")
    public ResponseEntity<String> get() {
        Service build = Service.builder().active(true)
                .categoryId("5").name("PDP Academy").
                requestAddress("https://click.uz/api/pay/")
                .number("4489449465465").fee(864654.55)
                .fields(new Field[]{Field.builder()
                        .name("invoice_number").type("text")
                        .required(true).build(), Field.builder()
                        .name("amount").type("number").required(true)
                        .build()}).build();

        Service insert = serviceRepository.insert(build);
        System.out.println("insert.toString() = " + insert);

        return ResponseEntity.ok("Ishladi, Chotki");

    }

    @PostMapping("/create")
    public ResponseEntity<Service> create(@RequestBody Service service) {
        Service savedService = serviceRepository.insert(service);
        return ResponseEntity.ok(savedService);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Service>> getAll() {
        List<Service> services = serviceRepository.findAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Service> getById(@PathVariable String id) {
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            return ResponseEntity.ok(serviceOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Service> update(@PathVariable String id,
                                          @RequestBody Service serviceDetails) {
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            Service service = serviceOptional.get();
            service.setCategoryId(serviceDetails.getCategoryId());
            service.setName(serviceDetails.getName());
            service.setRequestAddress(serviceDetails.getRequestAddress());
            service.setNumber(serviceDetails.getNumber());
            service.setFee(serviceDetails.getFee());
            service.setFields(serviceDetails.getFields());
            Service updatedService = serviceRepository.save(service);
            return ResponseEntity.ok(updatedService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
