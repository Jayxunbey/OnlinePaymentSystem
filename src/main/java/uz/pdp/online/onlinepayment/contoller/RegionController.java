package uz.pdp.online.onlinepayment.contoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.entity.inpostgres.Region;
import uz.pdp.online.onlinepayment.service.RegionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Integer id) {
        Optional<Region> region = regionService.getRegionById(id);
        return region.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        return ResponseEntity.status(HttpStatus.CREATED).body(regionService.createOrUpdateRegion(region));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable Integer id, @RequestBody Region region) {
        region.setId(id);
        return ResponseEntity.ok(regionService.createOrUpdateRegion(region));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Integer id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}
