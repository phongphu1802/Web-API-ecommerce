package com.spring.vn.Controller;

import com.spring.vn.Entity.ResponseObject;
import com.spring.vn.Entity.TypeProduct;
import com.spring.vn.Services.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/typeproduct")
public class TypeProductController {
    @Autowired
    private TypeProductService typeProductService;

    @GetMapping("")
    //this request is: http://localhost:8080/api/typeproduct
    ResponseEntity<ResponseObject> getAllTypeMember() {
        List<TypeProduct> typeProduct = typeProductService.getAllTypeProduct();
        return typeProduct.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find typeproduct", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query typeproduct successfully", typeProduct)
                );
    }

    //Find By ID
    @GetMapping("/{id}")
    //this request is: http://localhost:8080/api/typeproduct/{id}
    ResponseEntity<ResponseObject> findByID(@PathVariable Long id) {
        Optional<TypeProduct> typeProduct = typeProductService.findTypeProductyID(id);
        return typeProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query typeproduct successfully", typeProduct)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find typeproduct with id = " + id, "")
                );
    }

    //Insert new TypeProduct with POST method
    @PostMapping("/insert")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/typeproduct/insert
    ResponseEntity<ResponseObject> insertTypeProduct(@RequestBody TypeProduct newTypeProduct) {
        //Test Name
        Optional<TypeProduct> typeProduct = typeProductService.findTypeProductByTypeProductName(newTypeProduct.getTypeName());
        return typeProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Typeproductname was used","")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Insert typeproduct successfully", typeProductService.addTypeProduct(newTypeProduct))
                );
    }

    @PutMapping("/{id}")
    //Postman : Raw, JSON
    //Update, upset = update if found, otherwise insert
    //this request is: http://localhost:8080/api/typeproduct/{id}
    ResponseEntity<ResponseObject> updateTypeProduct(@RequestBody TypeProduct newTypeProduct, @PathVariable long id){
        Optional<TypeProduct> updateTypeProduct = Optional.ofNullable(typeProductService.findTypeProductyID(id)
                .map(TypeProduct -> {
                    TypeProduct.setTypeName(newTypeProduct.getTypeName());
                    TypeProduct.setState(newTypeProduct.getState());
                    return typeProductService.addTypeProduct(TypeProduct);
                }).orElseGet(() -> {
                    newTypeProduct.setTypeProductID(id);
                    return typeProductService.addTypeProduct(newTypeProduct);
                }));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update typeproduct successfully",updateTypeProduct)
        );
    }

    //Delete a TypeProduct
    @DeleteMapping("/{id}")
    //this request is: http://localhost:8080/api/typeproduct/{id}
    ResponseEntity<ResponseObject> deleteTypeProduct(@PathVariable long id){
        boolean exists = typeProductService.findTypeProductByID(id);
        if(exists){
            typeProductService.deleteTypeProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete typeproduct successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Can find typeproduct to delete","")
        );
    }
}
