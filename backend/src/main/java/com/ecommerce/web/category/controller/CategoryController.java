package com.ecommerce.web.category.controller;

import com.ecommerce.web.category.dto.CategoryDTO;
import com.ecommerce.web.category.service.CategoryService;
import com.ecommerce.web.category.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private CategoryService _service;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDto ) {
        CategoryDTO savedCategory =  this._service.createCategory(categoryDto);
        return new ResponseEntity<CategoryDTO>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> categories = this._service.getCategories();
        return new ResponseEntity<List<CategoryDTO>>(categories, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") String categoryId ) {
        CategoryDTO category = this._service.getCategory( categoryId );
        return new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") String categoryId, @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setId(categoryId);

        CategoryDTO updated = this._service.updateCategory(categoryDTO);
        return new ResponseEntity<CategoryDTO>(updated, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") String categoryId) {
        this._service.deleteCategory(categoryId);
        return new ResponseEntity<String>("Delete Success", HttpStatus.OK);
    }
}
