package uz.pdp.online.onlinepayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.dto.signup.resp.ApiResultDTO;
import uz.pdp.online.onlinepayment.dto.signup.req.RegionReqDTO;
import uz.pdp.online.onlinepayment.entity.inpostgres.Region;
import uz.pdp.online.onlinepayment.repo.inpostgres.RegionRepository;

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

    private void checkUnique(RegionReqDTO regionReqDTO) {
        if (regionRepository.existsByName(regionReqDTO.getName())) {
            throw new RuntimeException("Region with this name already exists");
        }
    }

    public ApiResultDTO<RegionReqDTO> createRegion(RegionReqDTO regionReqDTO) {
        checkUnique(regionReqDTO);

        Region region = new Region();
        region.setName(regionReqDTO.getName());
        regionRepository.save(region);

        RegionReqDTO createdRegionReqDTO = new RegionReqDTO();
        createdRegionReqDTO.setName(region.getName());

        return ApiResultDTO.success(createdRegionReqDTO);
    }

    public Region updateRegion(Integer id, RegionReqDTO regionReqDTO) {
        Region region = getRegionById(id);
        region.setName(regionReqDTO.getName());
        return regionRepository.save(region);
    }

    public void deleteRegion(Integer id) {
        regionRepository.deleteById(id);
    }
}
