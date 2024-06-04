package uz.pdp.online.onlinepayment.repo.inmongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import uz.pdp.online.onlinepayment.entity.inmongo.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends MongoRepository<Service, String> {

    long countById(String id);

    Optional<Service> findByNumber(Integer number);

    List<Service> findByCategoryId(String categoryId);

    List<Service> findByCategoryIdAndActiveTrue(String categoryId);
}
