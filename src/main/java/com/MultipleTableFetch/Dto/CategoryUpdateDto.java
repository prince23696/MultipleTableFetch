package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.SubCategory;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDto {

    public String categorySequence;
    public String categoryName;
    //public List<SubCategory> subCategoryList;
}
