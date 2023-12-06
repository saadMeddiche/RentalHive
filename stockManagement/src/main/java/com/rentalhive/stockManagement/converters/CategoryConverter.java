package com.rentalhive.stockManagement.converters;

import com.rentalhive.stockManagement.dto.categoryDtos.response.CategoryResponseDto;
import com.rentalhive.stockManagement.entities.Category;

public  class  CategoryConverter {

    public static CategoryResponseDto convertToDto(Category category){

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

        categoryResponseDto.setId(category.getId());

        categoryResponseDto.setName(category.getName());

        return categoryResponseDto;
    }
}
