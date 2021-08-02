package com.shopping.service.impl;

import com.shopping.model.Category;
import com.shopping.repository.CategoryRepository;
import com.shopping.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

}
