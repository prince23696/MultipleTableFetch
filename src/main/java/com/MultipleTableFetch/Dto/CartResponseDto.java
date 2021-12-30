package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.Cart;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {

    private int recordCount;
    private List<Cart> cartList;
    private Cart cartDto;
}