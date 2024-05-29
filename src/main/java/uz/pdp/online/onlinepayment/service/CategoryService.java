package uz.pdp.online.onlinepayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.common.exceptions.handling.CategoryExceptionHandler;
import uz.pdp.online.onlinepayment.dto.signup.req.CategorySaveDto;
import uz.pdp.online.onlinepayment.dto.signup.req.CategoryUpdateDto;
import uz.pdp.online.onlinepayment.entity.inmongo.Category;
import uz.pdp.online.onlinepayment.repo.inmongo.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CacheManager cacheManager;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CacheManager cacheManager) {
        this.categoryRepository = categoryRepository;
        this.cacheManager = cacheManager;
    }

    public void createCategory(CategorySaveDto categorySaveDto) {
        try {
            Category category = new Category();
            String number = null;

            Cache categoryCache = cacheManager.getCache("number_for_entities");
            if (categoryCache != null) {
                Integer cacheNumber = categoryCache.get("category", Integer.class);
                if (cacheNumber == null) {
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
        } catch (Exception ex) {
            throw new CategoryExceptionHandler.CategoryCreateException("Failed to create category: " + ex.getMessage());
        }
    }

    public void updateCategory(String number, CategoryUpdateDto categoryUpdateDto) {
        try {
            Category category = categoryRepository.findByNumber(number);
            if (category == null) {
                throw new CategoryExceptionHandler.CategoryNotFoundException("Category with number " + number + " not found");
            }
            category.setName(categoryUpdateDto.getName());
            category.setType(categoryUpdateDto.getType());
            category.setActive(categoryUpdateDto.isActive());
            categoryRepository.save(category);
        } catch (Exception ex) {
            throw new CategoryExceptionHandler.CategoryUpdateException("Failed to update category: " + ex.getMessage());
        }
    }

    public List<Category> showAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(String number) {
        try {
            Category category = categoryRepository.findByNumber(number);
            if (category == null) {
                throw new CategoryExceptionHandler.CategoryNotFoundException("Category with number " + number + " not found");
            }
            category.setActive(false);
            categoryRepository.save(category);
        } catch (Exception ex) {
            throw new CategoryExceptionHandler.CategoryDeleteException("Failed to delete category: " + ex.getMessage());
        }
    }
}
