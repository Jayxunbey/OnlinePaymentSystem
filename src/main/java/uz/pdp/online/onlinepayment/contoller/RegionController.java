package uz.pdp.online.onlinepayment.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.dto.signup.resp.ApiResultDTO;
import uz.pdp.online.onlinepayment.dto.signup.req.RegionReqDTO;
import uz.pdp.online.onlinepayment.entity.inpostgres.Region;
import uz.pdp.online.onlinepayment.service.RegionService;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@Secured("ROLE_ADMIN")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Integer id) {
        Region region = regionService.getRegionById(id);
        return ResponseEntity.ok(region);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResultDTO<RegionReqDTO>> createRegion(@RequestBody RegionReqDTO regionReqDTO) {
        ApiResultDTO<RegionReqDTO> result = regionService.createRegion(regionReqDTO);
        return ResponseEntity.status(result.isSuccess() ? 201 : 400).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable Integer id, @RequestBody RegionReqDTO regionReqDTO) {
        Region updatedRegion = regionService.updateRegion(id, regionReqDTO);
        return ResponseEntity.ok(updatedRegion);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Integer id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}
