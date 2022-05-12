package com.spring.vn.Services;

import java.util.List;
import java.util.Optional;

import com.spring.vn.Entity.Product;
import com.spring.vn.Entity.TypeProduct;
import com.spring.vn.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public Optional<Product> findProductID(String productID){return productRepository.findById(productID);}
    public void deteleProductByID(String productID){
        productRepository.deleteById(productID);
    }
    public List<Product> getProductByState(int state){
        return productRepository.findByState(state);
    }
    public Optional<Product> findProductByProductName(String productName){return productRepository.findProductByProductName(productName);}
    public List<Product> getProductByType(TypeProduct typeProduct){return productRepository.findByTypeProduct(typeProduct);}

    public Boolean findProductByID(String id){return productRepository.existsById(id);}
    public Boolean deleteProduct(String typeProductID){productRepository.deleteById(typeProductID);return true;}
}
