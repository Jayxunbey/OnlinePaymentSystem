package uz.pdp.online.onlinepayment.repo.inmongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.online.onlinepayment.entity.inmongo.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Integer> {
    Optional<Category> findById(String id);

    long countById(String id);

    Category findByNumber(String number);

    boolean existsByNumber(String number);
}
