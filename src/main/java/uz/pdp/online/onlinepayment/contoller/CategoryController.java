package uz.pdp.online.onlinepayment.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.onlinepayment.dto.signup.req.CategorySaveDto;
import uz.pdp.online.onlinepayment.dto.signup.req.CategoryUpdateDto;
import uz.pdp.online.onlinepayment.entity.inmongo.Category;
import uz.pdp.online.onlinepayment.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Secured("ROLE_ADMIN")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody CategorySaveDto categorySaveDto) {
        categoryService.createCategory(categorySaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update-category/{number}")
    public ResponseEntity<?> updateCategory(@PathVariable String number, @RequestBody CategoryUpdateDto categoryUpdateDto) {
        categoryService.updateCategory(number, categoryUpdateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/showAllCategories")
    public ResponseEntity<List<Category>> showAllCategory() {
        List<Category> categories = categoryService.showAllCategories();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/delete-categories/{number}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String number) {
        categoryService.deleteCategory(number);
        return ResponseEntity.noContent().build();
    }
}
