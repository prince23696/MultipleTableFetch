package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.Category;
import com.MultipleTableFetch.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    @Query(value = "select count(sub_category_id) from sub_category ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select * from sub_category c where category_id=?1 ", nativeQuery = true)
    public List<SubCategory> findBySubCategoryDetailsDto(int categoryId);

    @Query(value = "select * from sub_category c where c.sub_category_id=?1", nativeQuery = true)
    public List<SubCategory> findBySubCategoryDetailsById(int id);

    @Query(value = "select * from sub_category c where c.sub_category_name like ?1% ", nativeQuery = true)
    public List<SubCategory> findBySubCategoryDetailsByName(String name);

    @Query(value = "select * from sub_category c where c.sub_category_id=?1 or c.sub_category_name like ?2%", nativeQuery = true)
    public List<SubCategory> findBySubCategoryDetailsByNameAndId(int id, String name);
}