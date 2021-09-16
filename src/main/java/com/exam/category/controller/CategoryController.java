package com.exam.category.controller;

import com.exam.category.exception.CategoryAlreadyExistsException;
import com.exam.category.exception.CategoryNotFoundException;
import com.exam.category.model.Category;
import com.exam.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping("/")
    public Category addCategory(@RequestBody Category category) throws CategoryAlreadyExistsException {
        return categoryService.addCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable("id") Integer categoryId) throws CategoryNotFoundException {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping("/{id}")
    public boolean removeCategory(@PathVariable("id") Integer categoryId){
        return categoryService.removeCategory(categoryId);
    }

    @PutMapping("/{id}")
    public boolean updateCategory(@PathVariable("id") Integer id, @RequestBody Category category){
        return categoryService.updateCategory(id, category);
    }
}
