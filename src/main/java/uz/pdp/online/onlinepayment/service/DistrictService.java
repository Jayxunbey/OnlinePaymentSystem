package uz.pdp.online.onlinepayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.dto.DistrictDTO;
import uz.pdp.online.onlinepayment.entity.inpostgres.District;
import uz.pdp.online.onlinepayment.entity.inpostgres.Region;
import uz.pdp.online.onlinepayment.repo.DistrictRepository;
import uz.pdp.online.onlinepayment.repo.RegionRepository;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private RegionRepository regionRepository;

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public District getDistrictById(Integer id) {
        return districtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("District not found with id: " + id));
    }

    public District createDistrict(DistrictDTO districtDTO) {
        District district = new District();
        district.setName(districtDTO.getName());

        Region region = regionRepository.findById(districtDTO.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region not found with id: " + districtDTO.getRegionId()));

        district.setRegion(region);

        return districtRepository.save(district);
    }

    public District updateDistrict(Integer id, DistrictDTO districtDTO) {
        District district = getDistrictById(id);
        district.setName(districtDTO.getName());

        Region region = regionRepository.findById(districtDTO.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region not found with id: " + districtDTO.getRegionId()));

        district.setRegion(region);

        return districtRepository.save(district);
    }

    public void deleteDistrict(Integer id) {
        districtRepository.deleteById(id);
    }
}
