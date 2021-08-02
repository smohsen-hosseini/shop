package com.shopping.service;

import com.shopping.model.Category;

import java.util.List;


public interface CategoryService {

    void save(Category category);
    List<Category> findAll();
    Category findByCategoryName(String categoryName);
}
