package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Category;
import com.rentalhive.stockManagement.exceptions.costums.EmptyListException;
import com.rentalhive.stockManagement.repositories.CategoryRepository;
import com.rentalhive.stockManagement.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.rentalhive.stockManagement.services.helpers.CategoryServiceHelper;

import java.util.List;
import java.util.Optional;

@Service

public class CategoryServiceImp extends CategoryServiceHelper implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategorys() {

        List<Category> categories = categoryRepository.findAll();

        if(categories.isEmpty()){
            throw new EmptyListException("There are no Categories");
        }

        return categories;
    }

    public Category findById(Long id) {

        throwExceptionIfIdOfCategoryIsNull(id);

        Optional<Category> user = categoryRepository.findById(id);

        thowExceptionIfCategoryIsEmpty(user);

        return user.get();
    }

    public Category addCategory(Category category) {
        return null;
    }

    public Category updateCategory(Category category) {
        return null;
    }

    public void deleteCategory(Category category) {

    }

    public boolean isExists(Category category) {
        return categoryRepository.existsById(category.getId());
    }
}
