package com.exam.category.service;

import com.exam.category.exception.CategoryAlreadyExistsException;
import com.exam.category.exception.CategoryNotFoundException;
import com.exam.category.model.Category;
import com.exam.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exam.common.constant.ExceptionConstants.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) throws CategoryAlreadyExistsException {
        final boolean categoryExistWithTitle = categoryRepository.categoryExistsByTitle(category.getTitle());

        if(categoryExistWithTitle){
            throw new CategoryAlreadyExistsException(CATEGORY_ALREADY_EXISTS+category.getTitle());
        }else{
            int newCategoryId = categoryRepository.addCategory(category);
            category.setId(newCategoryId);
        }

        return category;
    }

    public Category getCategoryById(Integer categoryId) throws CategoryNotFoundException {
        final Category category = categoryRepository.findById(categoryId);
        if(category == null) {
            throw new CategoryNotFoundException(CATEGORY_NOT_FOUND_FOR_ID+categoryId);
        }
        return category;
    }

    public Category getCategoryByTitle(String title) throws CategoryNotFoundException {
        final Category category = categoryRepository.findByTitle(title);
        if(category == null) {
            throw new CategoryNotFoundException(CATEGORY_NOT_FOUND_FOR_TITLE+title);
        }
        return category;
    }

    public List<Category> getCategoriesBySubjectId(Integer subjectId) throws CategoryNotFoundException {
        final List<Category> categories = categoryRepository.findBySubjectId(subjectId);
        if(categories == null || categories.isEmpty()) {
            throw new CategoryNotFoundException(CATEGORY_NOT_FOUND_FOR_SUBJECT_ID+subjectId);
        }
        return categories;
    }

    public boolean removeCategory(Integer categoryId){
        return categoryRepository.delete(categoryId);
    }

    public boolean removeAllCategories(){
        return categoryRepository.deleteAll();
    }

    public boolean removeCategoriesByIds(List<Integer> ids){
        return categoryRepository.deleteByIds(ids);
    }

    public boolean updateCategory(Integer id, Category category){
        return categoryRepository.updateCategory(id, category);
    }
}
