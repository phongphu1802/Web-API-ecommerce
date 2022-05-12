package com.spring.vn.Repository;

import java.util.List;
import java.util.Optional;

import com.spring.vn.Entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    public List<Product> findByState(int state);
    public Optional<Product> findProductByProductName(String productName);
    public List<Product> findByTypeProduct(TypeProduct typeProduct);
}
