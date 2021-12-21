package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.SubCategory;

import java.util.List;

public interface SubCategoryService {

    public List<SubCategory> getAllSubCategory();

    public SubCategory getSubCategory(int id);

    public SubCategory addSubCategory(SubCategory subCategory);

    public SubCategory updateSubCategory(int id, SubCategory subCategory);

    public SubCategory deleteSubCategory(int id);

}
