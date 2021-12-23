package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.SubCategoryUpdateDto;
import com.MultipleTableFetch.Entity.Category;
import com.MultipleTableFetch.Entity.SubCategory;
import com.MultipleTableFetch.Repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    CategoryService categoryService;

    @Override
    public List<SubCategory> getAllSubCategory(int categoryId) {
        return subCategoryRepository.findBySubCategoryDetailsDto(categoryId);
    }

    @Override
    public SubCategory getSubCategory(int id) {
        return subCategoryRepository.findById(id).get();
    }

    @Override
    public SubCategory addSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);

    }

    @Override
    public SubCategory updateSubCategory(int id, SubCategoryUpdateDto subCategory) {
        SubCategory subCategory1 = subCategoryRepository.findById(id).get();
        subCategory1.setCategoryId(subCategory.getCategoryId());
        subCategory1.setSubCategoryName(subCategory.getSubCategoryName());
        subCategory1.setSubCategorySequence(subCategory.getSubCategorySequence());
        subCategory1.setCategory(categoryService.getCategory(subCategory.getCategoryId()));
        subCategoryRepository.save(subCategory1);
        return subCategory1;
    }

    @Override
    public SubCategory deleteSubCategory(int id) {
        SubCategory subCategory = subCategoryRepository.findById(id).get();
        subCategoryRepository.deleteById(id);
        return subCategory;
    }

    @Override
    public List<SubCategory> getSubCategoryByNameOrId(String name, Integer id) {
        if (name == null && id != null) {
            List<SubCategory> bySubCategoryDetailsById = subCategoryRepository.findBySubCategoryDetailsById(id);
            return bySubCategoryDetailsById;
        } else if (name != null && id == null) {
            List<SubCategory> bySubCategoryDetailsByName = subCategoryRepository.findBySubCategoryDetailsByName(name);
            return bySubCategoryDetailsByName;
        } else if (name != null && id != null) {
            List<SubCategory> bySubCategoryDetailsByName = subCategoryRepository.findBySubCategoryDetailsByNameAndId(id, name);
            return bySubCategoryDetailsByName;
        } else {
            List<SubCategory> bySubCategoryDetailsByName = subCategoryRepository.findAll();
            return bySubCategoryDetailsByName;
        }
    }
}