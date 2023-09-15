package com.example.algamoney.api.services;

import com.example.algamoney.api.models.CategoryModel;
import com.example.algamoney.api.models.PersonModel;
import com.example.algamoney.api.repositories.CategoryRepository;
import com.example.algamoney.api.repositories.PersonRepository;
import com.example.algamoney.api.resources.PersonResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel updateCategory(Long id, CategoryModel categoryModel) {
        CategoryModel savedCategory = this.categoryRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(categoryModel, savedCategory, "id");
        return categoryRepository.save(savedCategory);
    }

}
