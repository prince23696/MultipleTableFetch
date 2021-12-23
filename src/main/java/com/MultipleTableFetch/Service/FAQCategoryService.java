package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.FAQCategoryDetailsResponseDto;
import com.MultipleTableFetch.Dto.FAQCategoryUpdateDto;
import com.MultipleTableFetch.Entity.FAQCategory;

import java.util.List;

public interface FAQCategoryService {

    public FAQCategoryDetailsResponseDto getAllFAQCategory();

    public List<FAQCategory> getAll();

    public FAQCategory getFAQCategory(int id);

    public FAQCategory addFAQCategory(FAQCategory faqCategory);

    public FAQCategory updateFAQCategory(int id, FAQCategoryUpdateDto faqCategoryUpdateDto);

    public FAQCategory deleteFAQCategory(int id);

    public List<FAQCategory> getFAQCategoryByNameOrId(Integer id, String name);
}