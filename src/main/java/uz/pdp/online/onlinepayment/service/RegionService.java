package uz.pdp.online.onlinepayment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.entity.inpostgres.Region;
import uz.pdp.online.onlinepayment.repo.RegionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionById(Integer id) {
        return regionRepository.findById(id);
    }

    public Region createOrUpdateRegion(Region region) {
        return regionRepository.save(region);
    }

    public void deleteRegion(Integer id) {
        regionRepository.deleteById(id);
    }
}

