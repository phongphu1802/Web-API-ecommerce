package com.spring.vn.Controller;

import com.spring.vn.Entity.Account;
import com.spring.vn.Entity.Product;
import com.spring.vn.Entity.ResponseObject;
import com.spring.vn.Entity.TypeProduct;
import com.spring.vn.Services.ProductService;
import com.spring.vn.Services.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private TypeProductService typeProductService;
    @GetMapping("")
    //this request is: http://localhost:8080/api/product
    ResponseEntity<ResponseObject> getAllProduct() {
        List<Product> products = productService.getAllProducts();
        return products.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", products)
                );
    }

    //Find By ID
    @GetMapping("/{id}")
    //this request is: http://localhost:8080/api/product/{id}
    ResponseEntity<ResponseObject> findByID(@PathVariable String id) {
        Optional<Product> product = productService.findProductID(id);
        return product.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", product)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with id = " + id, "")
                );
    }

    //Find By name product
    @GetMapping("typeproduct/{name}")
    //this request is: http://localhost:8080/api/product/typeproduct/{name}
    ResponseEntity<ResponseObject> findByType(@PathVariable String name) {
        Optional<TypeProduct> typeProduct = typeProductService.findTypeProductByTypeProductName(name);
        List<Product> product = productService.getProductByType(typeProduct.get());
        return product.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", product)
                );
    }


    //Insert new Product with POST method
    @PostMapping("/insert")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/product/insert
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        //Test Name
        Optional<Product> product = productService.findProductByProductName(newProduct.getProductName());
        return product.isPresent() ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Product name was used","")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Insert product successfully", productService.addProduct(newProduct))
                );
    }

    @PutMapping("/{id}")
        //Postman : Raw, JSON
        //Update, upset = update if found, otherwise insert
        //this request is: http://localhost:8080/api/product/{id}
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable String id){
        Optional<Product> updateProduct = Optional.ofNullable(productService.findProductID(id)
                .map(Product -> {
                    Product.setProductName(newProduct.getProductName());
                    Product.setImages(newProduct.getImages());
                    Product.setInfo(newProduct.getInfo());
                    Product.setPrice(newProduct.getPrice());
                    Product.setLikes(newProduct.getLikes());
                    Product.setState(newProduct.getState());
                    Product.setPercent_discount(newProduct.getPercent_discount());
                    Product.setQuantity(newProduct.getQuantity());
                    Product.setSpecial_from_time(newProduct.getSpecial_from_time());
                    Product.setSpecial_to_time(newProduct.getSpecial_to_time());
                    Product.setViews(newProduct.getViews());
                    Product.setTypeProduct(newProduct.getTypeProduct());
                    return productService.addProduct(Product);
                }).orElseGet(() -> {
                    newProduct.setProductID(id);
                    return productService.addProduct(newProduct);
                }));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product successfully", updateProduct)
        );
    }

    //Delete a Product
    @DeleteMapping("/{id}")
    //this request is: http://localhost:8080/api/product/{id}
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable String id){
        boolean exists = productService.findProductByID(id);
        if(exists){
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete transport successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Can find transport to delete","")
        );
    }

    //Update views
    @PutMapping("views/{id}")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/product/views/{id}
    ResponseEntity<ResponseObject> updateViewsProduct(@PathVariable String id){
        Product updateProduct = productService.findProductID(id).get();
        updateProduct.setViews(updateProduct.getViews()+1);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product views successfully",productService.addProduct(updateProduct))
        );
    }

    //Update state
    @PutMapping("state/{id}/{state}")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/product/state/{id}/{state}
    ResponseEntity<ResponseObject> updateStateProduct(@PathVariable String id,@PathVariable int state){
        Product updateProduct = productService.findProductID(id).get();
        updateProduct.setState(state);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product state successfully",productService.addProduct(updateProduct))
        );
    }
}
