package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.*;
import com.MultipleTableFetch.Entity.Category;
import com.MultipleTableFetch.Entity.FAQCategory;
import com.MultipleTableFetch.Entity.Subject;
import com.MultipleTableFetch.Repository.FAQCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQCategoryServiceImpl implements FAQCategoryService {

    @Autowired
    FAQCategoryRepository faqCategoryRepository;

    @Override
    public FAQCategoryDetailsResponseDto getAllFAQCategory() {
        int i = faqCategoryRepository.countRecords();
        List<FAQCategoryDetailsDto> byCategoryDetailsDto = faqCategoryRepository.findByFAQCategoryDetailsDto();
        FAQCategoryDetailsResponseDto faqCategoryDetailsResponseDto = new FAQCategoryDetailsResponseDto();
        faqCategoryDetailsResponseDto.setRecordCount(i);
        faqCategoryDetailsResponseDto.setFaqCategoryDetailsDtoList(byCategoryDetailsDto);
        return faqCategoryDetailsResponseDto;
    }

    @Override
    public List<FAQCategory> getAll() {
        return faqCategoryRepository.findAll();
    }

    @Override
    public FAQCategory getFAQCategory(int id) {
        return faqCategoryRepository.findById(id).get();
    }

    @Override
    public FAQCategory addFAQCategory(FAQCategory faqCategory) {
        return faqCategoryRepository.save(faqCategory);
    }

    @Override
    public FAQCategory updateFAQCategory(int id, FAQCategoryUpdateDto faqCategoryUpdateDto) {
        FAQCategory faqCategory = faqCategoryRepository.findById(id).get();
        faqCategory.setCategoryName(faqCategoryUpdateDto.getCategoryName());
        faqCategoryRepository.save(faqCategory);
        return faqCategory;
    }

    @Override
    public FAQCategory deleteFAQCategory(int id) {
        FAQCategory faqCategory = faqCategoryRepository.findById(id).get();
        faqCategoryRepository.deleteById(id);
        return faqCategory;
    }

    @Override
    public List<FAQCategory> getFAQCategoryByNameOrId(Integer id, String name) {
        if (name == null && id != null) {
            List<FAQCategory> byFAQCategoryDetailsById = faqCategoryRepository.findByFAQCategoryDetailsById(id);
            return byFAQCategoryDetailsById;
        } else if (name != null && id == null) {
            List<FAQCategory> byFAQCategoryDetailsByName = faqCategoryRepository.findByFAQCategoryDetailsByName(name);
            return byFAQCategoryDetailsByName;
        } else if (name != null && id != null) {
            List<FAQCategory> byFAQCategoryDetailsByName = faqCategoryRepository.findByFAQCategoryDetailsByNameAndId(id, name);
            return byFAQCategoryDetailsByName;
        } else {
            List<FAQCategory> all = faqCategoryRepository.findAll();
            return all;
        }
    }
}