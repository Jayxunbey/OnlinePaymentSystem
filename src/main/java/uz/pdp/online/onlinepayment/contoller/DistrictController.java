package uz.pdp.online.onlinepayment.contoller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.dto.signup.resp.ApiResultDTO;
import uz.pdp.online.onlinepayment.dto.signup.req.DistrictReqDTO;
import uz.pdp.online.onlinepayment.entity.inpostgres.District;
import uz.pdp.online.onlinepayment.service.DistrictService;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
@Tag(name = "District",description = "You can modify on Districts when you was Admin")
@Secured("ROLE_ADMIN")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<District>> getAllDistricts() {
        List<District> allDistricts = districtService.getAllDistricts();
        System.out.println("allDistricts = " + allDistricts);
        return ResponseEntity.ok(allDistricts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable Integer id) {

        District district = districtService.getDistrictById(id);
        return ResponseEntity.ok(district);
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResultDTO<DistrictReqDTO>> createDistrict(@RequestBody DistrictReqDTO districtReqDTO) {
        ApiResultDTO<DistrictReqDTO> result = districtService.createDistrict(districtReqDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 400).body(result);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<District> updateDistrict(@PathVariable Integer id, @RequestBody DistrictReqDTO districtReqDTO) {
        District updatedDistrict = districtService.updateDistrict(id, districtReqDTO);
        return ResponseEntity.ok(updatedDistrict);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Integer id) {
        districtService.deleteDistrict(id);
        return ResponseEntity.noContent().build();
    }
}
