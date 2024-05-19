package uz.pdp.online.onlinepayment.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.dto.DistrictDTO;
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

    @PostMapping
    public ResponseEntity<District> createDistrict(@RequestBody DistrictDTO districtDTO) {
        District district = districtService.createDistrict(districtDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(district);
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
