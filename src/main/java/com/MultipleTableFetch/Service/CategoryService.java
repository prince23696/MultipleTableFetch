package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategory();

    public Category getCategory(int id);

    public Category addCategory(Category category);

    public Category updateCategory(int id, Category category);

    public Category deleteCategory(int id);
}
