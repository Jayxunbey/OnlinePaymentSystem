package uz.pdp.online.onlinepayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.entity.inpostgres.District;
import uz.pdp.online.onlinepayment.repo.DistrictRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public Optional<District> getDistrictById(Integer id) {
        return districtRepository.findById(id);
    }

    public District createOrUpdateDistrict(District district) {
        return districtRepository.save(district);
    }

    public void deleteDistrict(Integer id) {
        districtRepository.deleteById(id);
    }
}
