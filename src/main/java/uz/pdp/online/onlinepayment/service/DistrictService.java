package uz.pdp.online.onlinepayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.dto.signup.req.ApiResultDTO;
import uz.pdp.online.onlinepayment.dto.signup.req.DistrictDTO;
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

    private void checkUnique(DistrictDTO districtDTO) {
        if (districtRepository.existsByName(districtDTO.getName())) {
            throw new RuntimeException("District with this name already exists");
        }
    }

    public ApiResultDTO<DistrictDTO> createDistrict(DistrictDTO districtDTO) {
        checkUnique(districtDTO);

        Region region = regionRepository.findById(districtDTO.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region not found with id: " + districtDTO.getRegionId()));

        District district = new District();
        district.setName(districtDTO.getName());
        district.setRegion(region);

        districtRepository.save(district);

        DistrictDTO createdDistrictDTO = new DistrictDTO();
        createdDistrictDTO.setName(district.getName());
        createdDistrictDTO.setRegionId(region.getId());

        return ApiResultDTO.success(createdDistrictDTO);
    }

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public District getDistrictById(Integer id) {
        return districtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("District not found with id: " + id));
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
