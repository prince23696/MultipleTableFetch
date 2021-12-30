package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.WishList;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WishListResponseDto {

    private int recordCount;
    private List<WishList> wishLists;
    private WishList wishList;
}