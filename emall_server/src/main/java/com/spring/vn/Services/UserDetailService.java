package com.spring.vn.Services;

import com.spring.vn.Entity.TypeProduct;
import com.spring.vn.Entity.UserDetail;
import com.spring.vn.Repository.TypeMemberRepository;
import com.spring.vn.Repository.UserDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailService {
    @Autowired
    private UserDetailRepository tvRepository;
    public UserDetail addUserDetail(UserDetail userDetail){return tvRepository.save(userDetail);}
    public List<UserDetail> getAllUserDetail(){
        return tvRepository.findAll();
    }
    public Optional<UserDetail> findUserDetailId(String userID){return tvRepository.findById(userID);}
    public Optional<UserDetail> findUserDetailPhoneNumber(String phoneNumber){return tvRepository.findUserDetailByPhone(phoneNumber);}
    public Optional<UserDetail> getUserDetailID(String userDetailID){return tvRepository.findById(userDetailID);}
    public Boolean findUserDetailByID(String userID){return tvRepository.existsById(userID);}
    public Boolean deleteUserDetail(String userID){tvRepository.deleteById(userID);return true;}
}