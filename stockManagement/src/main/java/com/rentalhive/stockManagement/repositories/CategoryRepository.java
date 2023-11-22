package com.rentalhive.stockManagement.repositories;

import com.rentalhive.stockManagement.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

