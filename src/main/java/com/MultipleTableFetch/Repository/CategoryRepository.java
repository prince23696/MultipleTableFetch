package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Dto.CategoryDetailsDto;
import com.MultipleTableFetch.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select count(category_id) from category ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select c.category_id as categoryId,c.category_name as categoryName,c.category_sequence as categorySequence from category c", nativeQuery = true)
    public List<CategoryDetailsDto> findByCategoryDetailsDto();

    @Query(value = "select c.category_id as categoryId,c.category_name as categoryName,c.category_sequence as categorySequence from category c where c.category_id=?1", nativeQuery = true)
    public List<CategoryDetailsDto> findByCategoryDetailsDtoById(int id);

    @Query(value = "select * from category c where c.category_id=?1", nativeQuery = true)
    public List<Category> findByCategoryDetailsById(int id);

    @Query(value = "select * from category c where c.category_name like %:name% ", nativeQuery = true)
    public List<Category> findByCategoryDetailsByName(String name);

    @Query(value = "select * from category c where c.category_id=?1 or c.category_name like ?2%", nativeQuery = true)
    public List<Category> findByCategoryDetailsByNameAndId(int id, String name);
}