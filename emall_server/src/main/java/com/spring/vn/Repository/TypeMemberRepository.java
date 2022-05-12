package com.spring.vn.Repository;

import com.spring.vn.Entity.Account;
import com.spring.vn.Entity.TypeMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeMemberRepository extends JpaRepository<TypeMember,Long>{
    public Optional<TypeMember> findTypeMemberByTypeName(String typeName);
}
