package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.CategoryDetailsResponseDto;
import com.MultipleTableFetch.Dto.CategoryUpdateDto;
import com.MultipleTableFetch.Entity.Category;

import java.util.List;

public interface CategoryService {

    public CategoryDetailsResponseDto getAllCategory();

    public List<Category> getAll();

    public Category getCategory(int id);

    public Category addCategory(Category category);

    public Category updateCategory(int id, CategoryUpdateDto category);

    public Category deleteCategory(int id);

    public List<Category> getCategoryByNameOrId(String name, Integer id);
}
