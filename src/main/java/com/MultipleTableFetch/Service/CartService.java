package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.CartResponseDto;
import com.MultipleTableFetch.Entity.WishList;
import com.MultipleTableFetch.Dto.WishListResponseDto;
import com.MultipleTableFetch.Entity.Cart;

import java.util.List;

public interface CartService {

    public CartResponseDto getAllCartItem();

    public CartResponseDto getCartItemById(Long id);

    public Cart addToCart(Long courseId, int userId);

    public Cart updateCartItem(Long id, Long courseId, int userId);

    public Cart deleteFromCartById(Long id);

    public List<Cart> findFromCartByUserId(int userId);


    public CartResponseDto deleteAllFromCart(int userId);

    public WishList addToWishList(Long courseId, int userId);

    public WishListResponseDto getAllWishList();

    public WishListResponseDto getWishListItemById(Long id);

    public WishListResponseDto deleteAllFromWishList(int userId);

    public WishList deleteFromWishListById(Long id);

    public List<WishList> findFromWishListByUserId(int userId);

}
