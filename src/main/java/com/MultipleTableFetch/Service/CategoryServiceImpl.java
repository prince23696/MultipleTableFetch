package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Category;
import com.MultipleTableFetch.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int id, Category category) {
        Category category1 = categoryRepository.findById(id).get();
        category1.setCategoryName(category.getCategoryName());
        category1.setCategorySequence(category.getCategorySequence());
        category1.setSubCategory(category.getSubCategory());
        categoryRepository.save(category1);
        return category1;
    }

    @Override
    public Category deleteCategory(int id) {
        Category category = categoryRepository.findById(id).get();
        categoryRepository.deleteById(id);
        return category;
    }
}
