package uz.pdp.online.onlinepayment.repo.inmongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import uz.pdp.online.onlinepayment.entity.inmongo.Service;

public interface ServiceRepository extends MongoRepository<Service, String> {

    long countById(String id);
}
