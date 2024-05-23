package uz.pdp.online.onlinepayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.dto.signup.resp.ApiResultDTO;
import uz.pdp.online.onlinepayment.dto.signup.req.DistrictReqDTO;
import uz.pdp.online.onlinepayment.entity.inpostgres.District;
import uz.pdp.online.onlinepayment.entity.inpostgres.Region;
import uz.pdp.online.onlinepayment.repo.inpostgres.DistrictRepository;
import uz.pdp.online.onlinepayment.repo.inpostgres.RegionRepository;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private RegionRepository regionRepository;

    private void checkUnique(DistrictReqDTO districtReqDTO) {
        if (districtRepository.existsByName(districtReqDTO.getName())) {
            throw new RuntimeException("District with this name already exists");
        }
    }

    public ApiResultDTO<DistrictReqDTO> createDistrict(DistrictReqDTO districtReqDTO) {
        checkUnique(districtReqDTO);

        Region region = regionRepository.findById(districtReqDTO.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region not found with id: " + districtReqDTO.getRegionId()));

        District district = new District();
        district.setName(districtReqDTO.getName());
        district.setRegion(region);

        districtRepository.save(district);

        DistrictReqDTO createdDistrictReqDTO = new DistrictReqDTO();
        createdDistrictReqDTO.setName(district.getName());
        createdDistrictReqDTO.setRegionId(region.getId());

        return ApiResultDTO.success(createdDistrictReqDTO);
    }

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public District getDistrictById(Integer id) {
        return districtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("District not found with id: " + id));
    }

    public District updateDistrict(Integer id, DistrictReqDTO districtReqDTO) {
        District district = getDistrictById(id);
        district.setName(districtReqDTO.getName());

        Region region = regionRepository.findById(districtReqDTO.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region not found with id: " + districtReqDTO.getRegionId()));

        district.setRegion(region);

        return districtRepository.save(district);
    }

    public void deleteDistrict(Integer id) {
        districtRepository.deleteById(id);
    }
}
