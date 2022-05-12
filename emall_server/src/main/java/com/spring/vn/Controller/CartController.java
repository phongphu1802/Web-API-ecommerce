package com.spring.vn.Controller;

import com.spring.vn.Entity.Cart;
import com.spring.vn.Entity.Product;
import com.spring.vn.Entity.ResponseObject;
import com.spring.vn.Entity.UserDetail;
import com.spring.vn.Services.CartService;
import com.spring.vn.Services.ProductService;
import com.spring.vn.Services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    //this request is: http://localhost:8080/api/cart
    ResponseEntity<ResponseObject> getAllCart() {
        List<Cart> carts = cartService.getAllCart();
        return carts.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", carts)
                );
    }

    //Find By ID
    @GetMapping("/{id}")
    //this request is: http://localhost:8080/api/cart/{id}
    ResponseEntity<ResponseObject> findByID(@PathVariable int id) {
        Optional<Cart> cart = cartService.findCartID(id);
        return cart.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", cart)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with id = " + id, "")
                );
    }

    //Find By ID user
    @GetMapping("/user/{id}")
    //this request is: http://localhost:8080/api/cart/user/{id}
    ResponseEntity<ResponseObject> findByIDUser(@PathVariable String id) {
        Optional<UserDetail> userDetail = userDetailService.findUserDetailId(id);
        List<Cart> cart = cartService.findCartByUserDetail(userDetail.get());
        if(cart.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Not find product by userID", "")
            );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Find product by userID successfully", cart)
            );
        }
    }

    //Insert new Cart with POST method
    @PostMapping("/insert")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/cart/insert
    ResponseEntity<ResponseObject> insertCart(@RequestBody Cart newCart) {
        Optional<Product> product = productService.findProductID(newCart.getProductID().getProductID());
        Optional<UserDetail> userDetail = userDetailService.findUserDetailId(newCart.getUserID().getUserID());
        List<Cart> carts = cartService.findCartByProductIDAndUserID(product.get(),userDetail.get());
        if(carts.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                     new ResponseObject("ok", "Insert cart successfully", cartService.addCart(newCart))
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                     new ResponseObject("failed", "Cart already exist", "")
             );
        }
    }

    //Delete a Cart
    @DeleteMapping("delete/{pid}/{uid}")
    //this request is: http://localhost:8080/api/cart/delete/{pid}/{uid
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable(name = "pid")String pid, @PathVariable(name = "uid")String uid){
        Optional<Product> product = productService.findProductID(pid);
        Optional<UserDetail> userDetail = userDetailService.findUserDetailId(uid);
        List<Cart> carts = cartService.findCartByProductIDAndUserID(product.get(),userDetail.get());
        if(!carts.isEmpty()){
            cartService.deleteCart(carts.get(0).getCardId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete cart successfully","")
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can find cart to delete", "")
            );
        }
    }

    //add new Cart with POST method
    @PostMapping("/insert/{pid}/{uid}/{quantity}")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/cart/insert/{pid}/{uid}/{quantity}
    ResponseEntity<ResponseObject> addCart(@PathVariable(name = "pid")String pid, @PathVariable(name = "uid")String uid, @PathVariable(name = "quantity")int quantity) {
        Optional<Product> product = productService.findProductID(pid);
        Optional<UserDetail> userDetail = userDetailService.findUserDetailId(uid);
        List<Cart> carts = cartService.findCartByProductIDAndUserID(product.get(),userDetail.get());
        Cart newCart = new Cart();
        newCart.setProductID(product.get());
        newCart.setUserID(userDetail.get());
        newCart.setQuantity(quantity);
        if(carts.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert cart successfully", cartService.addCart(newCart))
            );
        }else{
            Cart addCart = carts.get(0);
            addCart.setQuantity(addCart.getQuantity()+quantity);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update cart successfully", updateCart(addCart,addCart.getCardId()))
            );
        }
    }

    //update new Cart with POST method
    @PostMapping("/update/{pid}/{uid}/{quantity}")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/cart/update/{pid}/{uid}/{quantity}
    ResponseEntity<ResponseObject> updateCart(@PathVariable(name = "pid")String pid, @PathVariable(name = "uid")String uid, @PathVariable(name = "quantity")int quantity) {
        Optional<Product> product = productService.findProductID(pid);
        Optional<UserDetail> userDetail = userDetailService.findUserDetailId(uid);
        List<Cart> carts = cartService.findCartByProductIDAndUserID(product.get(),userDetail.get());
        if(!carts.isEmpty()){
            Cart addCart = carts.get(0);
            addCart.setQuantity(quantity);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update cart successfully", updateCart(addCart,addCart.getCardId()))
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Update cart failed", "")
            );
        }
    }

    public int updateCart(Cart newCart, int id){
        Optional<Cart> updateCart = cartService.findCartID(id);
        if(updateCart.isPresent()) {
            updateCart.get().setProductID(newCart.getProductID());
            updateCart.get().setUserID(newCart.getUserID());
            updateCart.get().setQuantity(newCart.getQuantity());
            cartService.addCart(updateCart.get());
            return 1;
        }else {
            return 0;
        }
    }
}
