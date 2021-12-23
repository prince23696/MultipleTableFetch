package com.MultipleTableFetch.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryUpdateDto {

    private int categoryId;
    private String subCategorySequence;
    private String subCategoryName;
}
