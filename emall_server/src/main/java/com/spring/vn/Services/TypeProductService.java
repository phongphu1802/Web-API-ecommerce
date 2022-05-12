package com.spring.vn.Services;

import com.spring.vn.Entity.TypeMember;

import com.spring.vn.Entity.TypeProduct;
import com.spring.vn.Repository.TypeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TypeProductService {
    @Autowired
    TypeProductRepository typeProductRepository;
    public TypeProduct addTypeProduct(TypeProduct typeProduct){return typeProductRepository.save(typeProduct);}
    public List<TypeProduct> getAllTypeProduct(){return typeProductRepository.findAll();}
    public Optional<TypeProduct> findTypeProductyID(long typeMemberID){return typeProductRepository.findById(typeMemberID);}
    public Optional<TypeProduct> findTypeProductByTypeProductName(String typeName){return typeProductRepository.findTypeProductByTypeName(typeName);}
    public Boolean findTypeProductByID(long id){return typeProductRepository.existsById(id);}
    public Boolean deleteTypeProduct(long typeProductID){typeProductRepository.deleteById(typeProductID);return true;}
}
