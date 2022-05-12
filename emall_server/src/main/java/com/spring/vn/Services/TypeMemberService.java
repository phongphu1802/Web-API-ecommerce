package com.spring.vn.Services;

import com.spring.vn.Entity.TypeMember;
import com.spring.vn.Repository.TypeMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TypeMemberService {
    @Autowired TypeMemberRepository typeMemberRepository;
    public TypeMember addTypeMember(TypeMember typeMember){return typeMemberRepository.save(typeMember);}
    public List<TypeMember> getAllTypeMember(){return typeMemberRepository.findAll();}
    public Optional<TypeMember> findTypeMemberID(long typeMemberID){return typeMemberRepository.findById(typeMemberID);}
    public Optional<TypeMember> findTypeMemberByTypeMemberName(String typeName){return typeMemberRepository.findTypeMemberByTypeName(typeName);}
    public Boolean findTypeMemberByID(long id){
        return typeMemberRepository.existsById(id);
    }
    public boolean deleteTypeMember(long typeMemberID){
        typeMemberRepository.deleteById(typeMemberID);
        return true;
    }
}
