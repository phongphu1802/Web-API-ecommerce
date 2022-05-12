package com.spring.vn.Repository;
import java.util.List;
import java.util.Optional;

import com.spring.vn.Entity.TypeProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProductRepository extends JpaRepository< TypeProduct,Long> {
    public Optional<TypeProduct> findTypeProductByTypeName(String name);
}
