package com.rentalhive.stockManagement.controllers;

import com.rentalhive.stockManagement.entities.Category;
import com.rentalhive.stockManagement.helpers.ControllerHelper;
import com.rentalhive.stockManagement.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController extends ControllerHelper {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories(){

        try {

            List<Category> categories = categoryService.getAllCategorys();

            return new ResponseEntity<>(categories, HttpStatus.OK);

        }catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }

    }

}
