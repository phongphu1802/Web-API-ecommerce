package com.spring.vn.Services;

import java.util.List;
import java.util.Optional;

import com.spring.vn.Entity.Cart;
import com.spring.vn.Entity.Product;
import com.spring.vn.Entity.UserDetail;
import com.spring.vn.Repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional(rollbackFor = Exception.class)
@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    public Cart addCart(Cart cart){
        return cartRepository.save(cart);
    }
    public List<Cart> getAllCart(){return cartRepository.findAll();}
    public Optional<Cart> findCartID(int cartID){return cartRepository.findById(cartID);}
    public List<Cart> findCartByUserDetail(UserDetail userDetail){
        return cartRepository.findCartByUserID(userDetail);
    }
    public List<Cart> findCartByProductIDAndUserID(Product product,UserDetail userDetail){
        return cartRepository.findCartByProductIDAndUserID(product,userDetail);
    }
    public Boolean findCartByID(int id){
        return cartRepository.existsById(id);
    }
    public boolean deleteCart(int id){
        cartRepository.deleteById(id);
        return true;
    }
}