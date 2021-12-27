package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Dto.FAQCategoryDetailsDto;
import com.MultipleTableFetch.Entity.FAQCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FAQCategoryRepository extends JpaRepository<FAQCategory, Integer> {

    @Query(value = "select count(faq_category_id) from faqcategory ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select f.faq_category_id as faqCategoryId,f.category_name as categoryName from faqcategory f", nativeQuery = true)
    public List<FAQCategoryDetailsDto> findByFAQCategoryDetailsDto();

    @Query(value = "select * from faqcategory f where f.faq_category_id=?1", nativeQuery = true)
    public List<FAQCategory> findByFAQCategoryDetailsById(int id);

    @Query(value = "select * from faqcategory f where f.category_name like %:name% ", nativeQuery = true)
    public List<FAQCategory> findByFAQCategoryDetailsByName(String name);

    @Query(value = "select * from faqcategory f where f.faq_category_id=:id or c.category_name like %:name%", nativeQuery = true)
    public List<FAQCategory> findByFAQCategoryDetailsByNameAndId(int id, String name);

    @Query(value = "SELECT EXISTS(select * from faqcategory f where f.category_name =:name)", nativeQuery = true)
    public Boolean findByFAQCategoryByCategoryName(String name);
}