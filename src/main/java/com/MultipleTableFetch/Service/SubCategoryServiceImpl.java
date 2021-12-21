package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.SubCategory;
import com.MultipleTableFetch.Repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Override
    public List<SubCategory> getAllSubCategory() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory getSubCategory(int id) {
        return subCategoryRepository.findById(id).get();
    }

    @Override
    public SubCategory addSubCategory(SubCategory subCategory) {
        SubCategory save = subCategoryRepository.save(subCategory);
        SubCategory subCategory1 = subCategoryRepository.findById(save.getSubCategoryId()).get();
        subCategory1.setCategory(save.getCategory());
        subCategoryRepository.save(subCategory1);
        return  subCategory1;
    }

    @Override
    public SubCategory updateSubCategory(int id, SubCategory subCategory) {
        SubCategory subCategory1 = subCategoryRepository.findById(id).get();
        subCategory1.setCategoryId(subCategory.getCategoryId());
        subCategory1.setSubCategoryName(subCategory.getSubCategoryName());
        subCategory1.setSubCategorySequence(subCategory.getSubCategorySequence());
        subCategoryRepository.save(subCategory1);
        return subCategory1;
    }

    @Override
    public SubCategory deleteSubCategory(int id) {
        SubCategory subCategory = subCategoryRepository.findById(id).get();
        subCategoryRepository.deleteById(id);
        return subCategory;
    }
}
