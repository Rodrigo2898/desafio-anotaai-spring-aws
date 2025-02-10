package com.rr.desafio_anota_ai.controller;

import com.rr.desafio_anota_ai.domain.category.Category;
import com.rr.desafio_anota_ai.domain.category.CategoryDto;
import com.rr.desafio_anota_ai.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDto categoryDto) {
         Category category = categoryService.insert(categoryDto);
         return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok().body(categories);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDto categoryData){
        Category updatedCategory = categoryService.update(id, categoryData);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
