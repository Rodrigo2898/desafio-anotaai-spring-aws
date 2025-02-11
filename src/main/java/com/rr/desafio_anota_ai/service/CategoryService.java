package com.rr.desafio_anota_ai.service;

import com.rr.desafio_anota_ai.domain.category.Category;
import com.rr.desafio_anota_ai.domain.category.CategoryDto;
import com.rr.desafio_anota_ai.domain.category.exception.CategoryNotFoundException;
import com.rr.desafio_anota_ai.repository.CategoryRepository;
import com.rr.desafio_anota_ai.service.aws.AwsSnsService;
import com.rr.desafio_anota_ai.service.aws.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final AwsSnsService snsService;

    public CategoryService(CategoryRepository categoryRepository, AwsSnsService snsService) {
        this.categoryRepository = categoryRepository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDto categoryDto) {
        Category category = new Category();
        category.setTitle(categoryDto.title());
        category.setDescription(categoryDto.description());

        category.setOwnerId(categoryDto.ownerId());
        snsService.publish(new MessageDto(category.toString()));

        return categoryRepository.save(category);
    }


    public Category update(String id, CategoryDto categoryData){
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        categoryRepository.save(category);
        snsService.publish(new MessageDto(category.toString()));

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
