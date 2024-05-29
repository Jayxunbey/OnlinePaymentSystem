package uz.pdp.online.onlinepayment.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.onlinepayment.dto.signup.req.PlasticCardAddReqDto;
import uz.pdp.online.onlinepayment.service.PlasticCardService;

import java.text.ParseException;

@RestController
@RequestMapping("/api/plastic-card")
@RequiredArgsConstructor
public class PlasticCardController {

    private final PlasticCardService plasticCardService;

    @PostMapping("/adding")
    public String addPlasticCard(@RequestBody @Valid PlasticCardAddReqDto plasticCardAddReqDto) throws ParseException {

        plasticCardService.addPlasticCard(plasticCardAddReqDto);

        return null;
    }


}
