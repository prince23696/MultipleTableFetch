package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.SubCategoryUpdateDto;
import com.MultipleTableFetch.Entity.SubCategory;

import java.util.List;

public interface SubCategoryService {

    public List<SubCategory> getAllSubCategory(int categoryId);

    public SubCategory getSubCategory(int id);

    public SubCategory addSubCategory(SubCategory subCategory);

    public SubCategory updateSubCategory(int id, SubCategoryUpdateDto subCategory);

    public SubCategory deleteSubCategory(int id);

    public List<SubCategory> getSubCategoryByNameOrId(String name, Integer id);
}