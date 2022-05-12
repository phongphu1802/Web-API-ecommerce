package com.spring.vn.Repository;
import java.util.List;
import java.util.Optional;

import com.spring.vn.Entity.TypeMember;
import com.spring.vn.Entity.TypeProduct;
import com.spring.vn.Entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
    public Optional<UserDetail> findUserDetailByPhone(String phone);
}
