package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.CartResponseDto;
import com.MultipleTableFetch.Entity.WishList;
import com.MultipleTableFetch.Dto.WishListResponseDto;
import com.MultipleTableFetch.Entity.Cart;
import com.MultipleTableFetch.Repository.CartRepository;
import com.MultipleTableFetch.Repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    WishListRepository wishListRepository;

    @Override
    public CartResponseDto getAllCartItem() {
        int i = cartRepository.countRecords();
        List<Cart> all = cartRepository.findAll();
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setRecordCount(i);
        cartResponseDto.setCartList(all);
        return cartResponseDto;
    }

    @Override
    public CartResponseDto getCartItemById(Long id) {
        int i = cartRepository.countRecords();
        Cart cart = cartRepository.findById(id).get();
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setRecordCount(i);
        cartResponseDto.setCartDto(cart);
        return cartResponseDto;
    }

    @Override
    public Cart addToCart(Long courseId, int userId) {
        Cart cart = new Cart();
        cart.setCourseId(courseId);
        cart.setUserId(userId);
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCartItem(Long id, Long courseId, int userId) {
        Cart cart = cartRepository.findById(id).get();
        cart.setCourseId(courseId);
        cart.setUserId(userId);
        return cartRepository.save(cart);
    }

    @Override
    public Cart deleteFromCartById(Long id) {
        Cart cart = cartRepository.findById(id).get();
        cartRepository.deleteById(id);
        return cart;
    }

    @Override
    public List<Cart> findFromCartByUserId(int userId) {
        return cartRepository.findFromCartByUserId(userId);
    }

    @Override
    public CartResponseDto deleteAllFromCart(int userId) {
        int i = cartRepository.countRecords();
        List<Cart> all = cartRepository.findFromCartByUserId(userId);
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setRecordCount(i);
        cartResponseDto.setCartList(all);
        cartRepository.deleteAllRecordFromCart(userId);
        return cartResponseDto;
    }

    @Override
    public WishList addToWishList(Long courseId, int userId) {
        WishList wishList = new WishList();
        wishList.setCourseId(courseId);
        wishList.setUserId(userId);
        return wishListRepository.save(wishList);
    }

    @Override
    public WishListResponseDto getAllWishList() {
        int i = wishListRepository.countRecords();
        List<WishList> all = wishListRepository.findAll();
        WishListResponseDto wishListResponseDto = new WishListResponseDto();
        wishListResponseDto.setRecordCount(i);
        wishListResponseDto.setWishLists(all);
        return wishListResponseDto;
    }

    @Override
    public WishListResponseDto getWishListItemById(Long id) {
        int i = wishListRepository.countRecords();
        WishList wishList = wishListRepository.findById(id).get();
        WishListResponseDto wishListResponseDto = new WishListResponseDto();
        wishListResponseDto.setRecordCount(i);
        wishListResponseDto.setWishList(wishList);
        return wishListResponseDto;
    }

    @Override
    public WishListResponseDto deleteAllFromWishList(int userId) {
        int i = wishListRepository.countRecords();
        List<WishList> all = wishListRepository.findFromWishListByUserId(userId);
        WishListResponseDto wishListResponseDto = new WishListResponseDto();
        wishListResponseDto.setRecordCount(i);
        wishListResponseDto.setWishLists(all);
        wishListRepository.deleteAllRecordFromWishList(userId);
        return wishListResponseDto;
    }

    @Override
    public WishList deleteFromWishListById(Long id) {
        WishList wishList = wishListRepository.findById(id).get();
        wishListRepository.deleteById(id);
        return wishList;
    }

    @Override
    public List<WishList> findFromWishListByUserId(int userId) {
        return wishListRepository.findFromWishListByUserId(userId);
    }
}