package uz.pdp.online.onlinepayment.contoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.entity.inpostgres.District;
import uz.pdp.online.onlinepayment.service.DistrictService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<District>> getAllDistricts() {
        return ResponseEntity.ok(districtService.getAllDistricts());
    }
//
    @GetMapping("/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable Integer id) {
        Optional<District> district = districtService.getDistrictById(id);
        return district.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<District> createDistrict(@RequestBody District district) {
        return ResponseEntity.status(HttpStatus.CREATED).body(districtService.createOrUpdateDistrict(district));
    }

    @PutMapping("/{id}")
    public ResponseEntity<District> updateDistrict(@PathVariable Integer id, @RequestBody District district) {
        district.setId(id);
        return ResponseEntity.ok(districtService.createOrUpdateDistrict(district));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Integer id) {
        districtService.deleteDistrict(id);
        return ResponseEntity.noContent().build();
    }
}
