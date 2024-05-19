package uz.pdp.online.onlinepayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.dto.RegionDTO;
import uz.pdp.online.onlinepayment.entity.inpostgres.Region;
import uz.pdp.online.onlinepayment.repo.RegionRepository;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region getRegionById(Integer id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region not found with id: " + id));
    }

    public Region createRegion(RegionDTO regionDTO) {
        Region region = new Region();
        region.setName(regionDTO.getName());
        return regionRepository.save(region);
    }

    public Region updateRegion(Integer id, RegionDTO regionDTO) {
        Region region = getRegionById(id);
        region.setName(regionDTO.getName());
        return regionRepository.save(region);
    }

    public void deleteRegion(Integer id) {
        regionRepository.deleteById(id);
    }
}
