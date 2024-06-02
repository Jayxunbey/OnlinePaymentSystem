package uz.pdp.online.onlinepayment.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Category", description = "Category management APIs")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Create a new category",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Category created successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\n\"error\": \"error message\"\n}")
                            )
                    )
            }
    )
    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody CategorySaveDto categorySaveDto) {
        categoryService.createCategory(categorySaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Update an existing category",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category updated successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Category not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\n\"error\": \"Category not found\"\n}")
                            )
                    )
            }
    )
    @PutMapping("/update-category/{number}")
    public ResponseEntity<?> updateCategory(@PathVariable String number, @RequestBody CategoryUpdateDto categoryUpdateDto) {
        categoryService.updateCategory(number, categoryUpdateDto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get all categories",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categories retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Category.class)
                            )
                    )
            }
    )
    @GetMapping("/showAllCategories")
    public ResponseEntity<List<Category>> showAllCategory() {
        List<Category> categories = categoryService.showAllCategories();
        return ResponseEntity.ok(categories);
    }

    @Operation(
            summary = "Delete a category",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Category deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Category not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\n\"error\": \"Category not found\"\n}")
                            )
                    )
            }
    )
    @DeleteMapping("/delete-categories/{number}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String number) {
        categoryService.deleteCategory(number);
        return ResponseEntity.noContent().build();
    }
}
