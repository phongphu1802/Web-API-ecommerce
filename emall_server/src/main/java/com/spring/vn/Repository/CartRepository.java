package com.spring.vn.Repository;

import java.util.List;
import java.util.Optional;

import com.spring.vn.Entity.Cart;

import com.spring.vn.Entity.Product;
import com.spring.vn.Entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
    public List<Cart> findCartByUserID(UserDetail detail);
    public List<Cart> findCartByProductIDAndUserID(Product product,UserDetail userDetail);
}
