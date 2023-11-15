package com.rentalhive.stockManagement.services.implementes;

import com.rentalhive.stockManagement.entities.Category;
import com.rentalhive.stockManagement.repositories.CategoryRepository;
import com.rentalhive.stockManagement.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private CategoryRepository categoryRepository;
    public List<Category> getAllCategorys() {
        return null;
    }
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    public Category addCategory(Category category) {
        return null;
    }

    public Category updateCategory(Category category) {
        return null;
    }

    public void deleteCategory(Category category) {

    }
}
