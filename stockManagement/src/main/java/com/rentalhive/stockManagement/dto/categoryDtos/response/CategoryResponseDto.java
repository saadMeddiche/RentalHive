package com.rentalhive.stockManagement.dto.categoryDtos.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoryResponseDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
