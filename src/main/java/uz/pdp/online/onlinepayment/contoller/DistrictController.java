package uz.pdp.online.onlinepayment.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.dto.signup.req.ApiResultDTO;
import uz.pdp.online.onlinepayment.dto.signup.req.DistrictDTO;
import uz.pdp.online.onlinepayment.entity.inpostgres.District;
import uz.pdp.online.onlinepayment.service.DistrictService;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<District>> getAllDistricts() {
        return ResponseEntity.ok(districtService.getAllDistricts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable Integer id) {
        District district = districtService.getDistrictById(id);
        return ResponseEntity.ok(district);
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResultDTO<DistrictDTO>> createDistrict(@RequestBody DistrictDTO districtDTO) {
        ApiResultDTO<DistrictDTO> result = districtService.createDistrict(districtDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 400).body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<District> updateDistrict(@PathVariable Integer id, @RequestBody DistrictDTO districtDTO) {
        District updatedDistrict = districtService.updateDistrict(id, districtDTO);
        return ResponseEntity.ok(updatedDistrict);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Integer id) {
        districtService.deleteDistrict(id);
        return ResponseEntity.noContent().build();
    }
}
