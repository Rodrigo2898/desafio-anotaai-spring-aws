package com.rr.desafio_anota_ai.service;

import com.rr.desafio_anota_ai.domain.category.Category;
import com.rr.desafio_anota_ai.domain.category.CategoryDto;
import com.rr.desafio_anota_ai.domain.category.exception.CategoryNotFoundException;
import com.rr.desafio_anota_ai.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category insert(CategoryDto categoryDto) {
        Category category = new Category();
        category.setTitle(categoryDto.title());
        category.setDescription(categoryDto.description());
        category.setOwnerId(categoryDto.ownerId());
        return categoryRepository.save(category);
    }


    public Category update(String id, CategoryDto categoryData){
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        categoryRepository.save(category);

        return category;
    }

    public void delete(String id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        categoryRepository.delete(category);
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getById(String id){
        return categoryRepository.findById(id);
    }
}
