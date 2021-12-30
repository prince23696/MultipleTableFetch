package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.CartResponseDto;
import com.MultipleTableFetch.Dto.ResponseHandler;
import com.MultipleTableFetch.Entity.Course;
import com.MultipleTableFetch.Entity.Users;
import com.MultipleTableFetch.Entity.WishList;
import com.MultipleTableFetch.Dto.WishListResponseDto;
import com.MultipleTableFetch.Entity.Cart;
import com.MultipleTableFetch.Repository.CartRepository;
import com.MultipleTableFetch.Repository.WishListRepository;
import com.MultipleTableFetch.Service.CartService;
import com.MultipleTableFetch.Service.CourseService;
import com.MultipleTableFetch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class CartController {

    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    WishListRepository wishListRepository;

    @GetMapping("getCartByCartId")
    public ResponseEntity<Object> getCreateCart(@RequestParam Long id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CartResponseDto assignment = cartService.getCartItemById(id);
        return ResponseHandler.response(assignment, "Successfully Getting Cart Details", true, HttpStatus.OK);
    }

    @GetMapping("/getAllCarts")
    public ResponseEntity<Object> getAllCreateCart(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CartResponseDto allCart = cartService.getAllCartItem();
        return ResponseHandler.response(allCart, "Successfully Getting All Cart Details", true, HttpStatus.OK);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<Object> addToCart(@RequestParam Long courseId, @RequestParam int userId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        Users userUsingId = userService.getUserUsingId(userId);
        if (userUsingId == null)
            return ResponseHandler.response("", "USER NOT FOUND", false, HttpStatus.BAD_REQUEST);
        Course course = courseService.getCourse(courseId);
        if (course == null)
            return ResponseHandler.response("", "COURSE NOT FOUND.", false, HttpStatus.BAD_REQUEST);
        Boolean aBoolean = cartRepository.checkRecordExistOrNot(courseId, userId);
        if (!aBoolean) {
            if (wishListRepository.checkRecordExistOrNot(courseId, userId)) {
                WishList wishListRecord = wishListRepository.getWishListRecord(courseId, userId);
                wishListRepository.delete(wishListRecord);
            }
            Cart cart = cartService.addToCart(courseId, userId);
            return ResponseHandler.response(cart, "Successfully Created Cart With Given Details", true, HttpStatus.OK);
        } else
            return ResponseHandler.response("", "ALREADY EXISTS", false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteFromCart")
    public ResponseEntity<Object> deleteFromCart(@RequestParam Long id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Cart cart = cartService.deleteFromCartById(id);
        return ResponseHandler.response(cart, "Successfully Performed Delete Operation.", true, HttpStatus.OK);
    }

    @GetMapping("/findFromCartByUserId")
    public ResponseEntity<Object> findFromCartByUserId(@RequestParam int userId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Users userUsingId = userService.getUserUsingId(userId);
        if (userUsingId == null)
            return ResponseHandler.response("", "USER NOT FOUND", false, HttpStatus.BAD_REQUEST);
        else {
            List<Cart> cart = cartService.findFromCartByUserId(userId);
            return ResponseHandler.response(cart, "Successfully Performed Delete Operation.", true, HttpStatus.OK);
        }
    }

    @PutMapping("/updateCartItem")
    public ResponseEntity<Object> updateCartItem(@RequestParam Long id, @RequestParam Long courseId, @RequestParam int userId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        Users userUsingId = userService.getUserUsingId(userId);
        if (userUsingId == null)
            return ResponseHandler.response("", "USER NOT FOUND", false, HttpStatus.BAD_REQUEST);
        Course course = courseService.getCourse(courseId);
        if (course == null)
            return ResponseHandler.response("", "COURSE NOT FOUND.", false, HttpStatus.BAD_REQUEST);
        Boolean aBoolean = cartRepository.checkRecordExistOrNot(courseId, userId);
        Cart cart = cartService.updateCartItem(id, courseId, userId);
        return ResponseHandler.response(cart, "Successfully Updated Cart Details", true, HttpStatus.OK);
    }

    @PostMapping("/addToWishList")
    public ResponseEntity<Object> addToWishList(@RequestParam Long courseId, @RequestParam int userId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Users userUsingId = userService.getUserUsingId(userId);
        if (userUsingId == null)
            return ResponseHandler.response("", "USER NOT FOUND", false, HttpStatus.BAD_REQUEST);
        Course course = courseService.getCourse(courseId);
        if (course == null)
            return ResponseHandler.response("", "COURSE NOT FOUND.", false, HttpStatus.BAD_REQUEST);
        Boolean aBoolean = wishListRepository.checkRecordExistOrNot(courseId, userId);
        if (!aBoolean) {
            if (cartRepository.checkRecordExistOrNot(courseId, userId)) {
                Cart cartRecord = cartRepository.getCartRecord(courseId, userId);
                cartRepository.delete(cartRecord);
            }
            WishList wishList = cartService.addToWishList(courseId, userId);
            return ResponseHandler.response(wishList, "Successfully Added To WishList", true, HttpStatus.OK);
        } else
            return ResponseHandler.response("", "ALREADY EXISTS", false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllWishList")
    public ResponseEntity<Object> getAllWishList(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        WishListResponseDto wishList = cartService.getAllWishList();
        return ResponseHandler.response(wishList, "Successfully Getting All WishList Details", true, HttpStatus.OK);
    }

    @GetMapping("getWishListItemById")
    public ResponseEntity<Object> getWishListItemById(@RequestParam Long id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        WishListResponseDto wishListItemById = cartService.getWishListItemById(id);
        return ResponseHandler.response(wishListItemById, "Successfully Getting WishList Details", true, HttpStatus.OK);
    }

    @PostMapping("/clearCart")
    public ResponseEntity<Object> ClearCart(@RequestParam int userId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Users userUsingId = userService.getUserUsingId(userId);
        if (userUsingId == null)
            return ResponseHandler.response("", "USER NOT FOUND", false, HttpStatus.BAD_REQUEST);
        else {
            CartResponseDto cartResponseDto = cartService.deleteAllFromCart(userId);
            return ResponseHandler.response(cartResponseDto, "Successfully Deleted All Cart Item", true, HttpStatus.OK);
        }
    }

    @PostMapping("/clearWishList")
    public ResponseEntity<Object> ClearWishList(@RequestParam int userId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Users userUsingId = userService.getUserUsingId(userId);
        if (userUsingId == null)
            return ResponseHandler.response("", "USER NOT FOUND", false, HttpStatus.BAD_REQUEST);
        else {
            WishListResponseDto wishList = cartService.deleteAllFromWishList(userId);
            return ResponseHandler.response(wishList, "Successfully Deleted All WishList Item", true, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteFromWishList")
    public ResponseEntity<Object> deleteFromWishListById(@RequestParam Long id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        WishList wishList = cartService.deleteFromWishListById(id);
        return ResponseHandler.response(wishList, "Successfully Performed Delete Operation.", true, HttpStatus.OK);
    }

    @GetMapping("/findFromWishListByUserId")
    public ResponseEntity<Object> findFromWishListByUserId(@RequestParam int userId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Users userUsingId = userService.getUserUsingId(userId);
        if (userUsingId == null)
            return ResponseHandler.response("", "USER NOT FOUND", false, HttpStatus.BAD_REQUEST);
        else {
            List<WishList> wishList = cartService.findFromWishListByUserId(userId);
            return ResponseHandler.response(wishList, "Successfully Performed Fetch Operation.", true, HttpStatus.OK);
        }
    }
}