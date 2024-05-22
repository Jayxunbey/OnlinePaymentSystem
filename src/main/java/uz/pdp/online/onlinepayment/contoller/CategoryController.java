package uz.pdp.online.onlinepayment.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.dto.signup.req.CategorySaveDto;
import uz.pdp.online.onlinepayment.dto.signup.req.CategoryUpdateDto;
import uz.pdp.online.onlinepayment.entity.inmongo.Category;
import uz.pdp.online.onlinepayment.repo.inmongo.CategoryRepository;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CacheManager cacheManager;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, Random random, CacheManager cacheManager) {
        this.categoryRepository = categoryRepository;
        this.cacheManager = cacheManager;
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody CategorySaveDto categorySaveDto) {
        Category category = new Category();
        String number=null;

        Cache categoryCache = cacheManager.getCache("number_for_entities");
        if (categoryCache!=null) {
            Integer cacheNumber = categoryCache.get("category", Integer.class);
            if (cacheNumber==null) {
                long count = categoryRepository.count();
                Integer i = Integer.valueOf(String.valueOf(321000 + count));
                categoryCache.put("category", i);
                number = String.valueOf(i);
            } else {
                int setNumber = cacheNumber + 1;
                categoryCache.put("category", setNumber);
                number = String.valueOf(setNumber);
            }
        }

        category.setNumber(number);
        category.setName(categorySaveDto.getName());
        category.setType(categorySaveDto.getType());
        category.setActive(categorySaveDto.isActive());
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update-category/{number}")
    public ResponseEntity<?> updateCategory(@PathVariable String number, @RequestBody CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryRepository.findByNumber(number);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        category.setName(categoryUpdateDto.getName());
        category.setType(categoryUpdateDto.getType());
        category.setActive(categoryUpdateDto.isActive());

        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/showAllCategories")
    public ResponseEntity<List<Category>> showAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/delete-categories/{number}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String number) {
        System.out.println("ishladi");
        Category category = categoryRepository.findByNumber(number);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        category.setActive(false);
        categoryRepository.save(category);
        return ResponseEntity.noContent().build();
    }
}