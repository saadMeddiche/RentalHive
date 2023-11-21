package com.rentalhive.stockManagement.services;

import com.rentalhive.stockManagement.entities.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategorys();

    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public void deleteCategory(Category category);

    public Category findById(Long id);
}
