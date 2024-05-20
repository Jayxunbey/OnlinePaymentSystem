package uz.pdp.online.onlinepayment.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.dto.SaveDto;
import uz.pdp.online.onlinepayment.entity.inmongo.Category;
import uz.pdp.online.onlinepayment.repo.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody SaveDto saveDto) {
        Category category = new Category();
        category.setNumber(saveDto.getNumber());
        category.setName(saveDto.getName());
        category.setType(saveDto.getType());
        category.setActive(saveDto.isActive());
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody SaveDto saveDto) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        category.setNumber(saveDto.getNumber());
        category.setName(saveDto.getName());
        category.setType(saveDto.getType());
        category.setActive(saveDto.isActive());
        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/showAllCategories")
    public ResponseEntity<List<Category>> showAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/delete-categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        category.setActive(false);
        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }
}