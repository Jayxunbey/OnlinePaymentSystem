package uz.pdp.online.onlinepayment.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.onlinepayment.jwt.JwtProvider;
import uz.pdp.online.onlinepayment.entity.inmongo.Field;
import uz.pdp.online.onlinepayment.entity.inmongo.Service;
import uz.pdp.online.onlinepayment.repo.inmongo.ServiceRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {

    private final ServiceRepository serviceRepository;

    private final JwtProvider jwtProvider;


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

    @GetMapping("/token")
    private ResponseEntity<String> getToken(){

        String token = jwtProvider.generateToken("Jayxun", true);
        return ResponseEntity.ok(token);

    }

}
