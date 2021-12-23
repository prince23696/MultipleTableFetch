package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.*;
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
    public CategoryDetailsResponseDto getAllCategory() {
        int i = categoryRepository.countRecords();
        List<CategoryDetailsDto> byCategoryDetailsDto = categoryRepository.findByCategoryDetailsDto();
        CategoryDetailsResponseDto categoryDetailsResponseDto = new CategoryDetailsResponseDto();
        categoryDetailsResponseDto.setRecordCount(i);
        categoryDetailsResponseDto.setCategoryDetailsDtoList(byCategoryDetailsDto);
        return categoryDetailsResponseDto;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category addCategory(Category category) {
        Category save = categoryRepository.save(category);
        return save;
    }

    @Override
    public Category updateCategory(int id, CategoryUpdateDto category) {
        Category category1 = categoryRepository.findById(id).get();
        category1.setCategoryName(category.getCategoryName());
        category1.setCategorySequence(category.getCategorySequence());
        categoryRepository.save(category1);
        return category1;
    }

    @Override
    public Category deleteCategory(int id) {
        Category category = categoryRepository.findById(id).get();
        categoryRepository.deleteById(id);
        return category;
    }

    @Override
    public List<Category> getCategoryByNameOrId(String name, Integer id) {
        if (name == null && id != null) {
            List<Category> byCategoryDetailsById = categoryRepository.findByCategoryDetailsById(id);
            return byCategoryDetailsById;
        } else if (name != null && id == null) {
            List<Category> byCategoryDetailsByName = categoryRepository.findByCategoryDetailsByName(name);
            return byCategoryDetailsByName;
        } else if (name != null && id != null) {
            List<Category> byCategoryDetailsByName = categoryRepository.findByCategoryDetailsByNameAndId(id, name);
            return byCategoryDetailsByName;
        } else {
            List<Category> byCategoryDetailsByName = categoryRepository.findAll();
            return byCategoryDetailsByName;
        }
    }
}
