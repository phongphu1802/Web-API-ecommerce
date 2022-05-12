package com.spring.vn.Controller;

import com.spring.vn.Entity.ResponseObject;
import com.spring.vn.Entity.TypeMember;
import com.spring.vn.Entity.TypeProduct;
import com.spring.vn.Entity.UserDetail;
import com.spring.vn.Services.TypeMemberService;
import com.spring.vn.Services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/userdetail")
public class UserDetailController {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private TypeMemberService typeMemberService;
    @GetMapping("")
    //this request is: http://localhost:8080/api/userdetail
    ResponseEntity<ResponseObject> getAllTypeMember() {
        List<UserDetail> userDetail = userDetailService.getAllUserDetail();
        return userDetail.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find userdetail", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query userdetail successfully", userDetail)
                );
    }

    //Find By ID
    @GetMapping("/{id}")
    //this request is: http://localhost:8080/api/userdetail/{id}
    ResponseEntity<ResponseObject> findByID(@PathVariable String id) {
        Optional<UserDetail> userDetail = userDetailService.getUserDetailID(id);
        return userDetail.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query userdetail successfully", userDetail)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find userdetail with id = " + id, "")
                );
    }

    //Insert new userdetail with POST method
    @PostMapping("/insert/{tid}")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/userdetail/insert/{tid}
    ResponseEntity<ResponseObject> insertUserDetail(@RequestBody UserDetail newUserDetail, @PathVariable(name = "tid")Long tid) {
        //Test Name
        TypeMember typeMember = typeMemberService.findTypeMemberID(tid).get();
        Optional<UserDetail> userDetail = userDetailService.findUserDetailPhoneNumber(newUserDetail.getPhone());
        newUserDetail.setTypeMember(typeMember);
        return userDetail.isPresent() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Userdetail with phone was used","")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Insert userdetail successfully", userDetailService.addUserDetail(newUserDetail))
                );
    }

    @PutMapping("/{id}")
        //Postman : Raw, JSON
        //Update, upset = update if found, otherwise insert
        //this request is: http://localhost:8080/api/userdetail/{id}
    ResponseEntity<ResponseObject> updateUserDetail(@RequestBody UserDetail newUserDetail, @PathVariable String id){
        Optional<UserDetail> updateUserDetail = Optional.ofNullable(userDetailService.findUserDetailId(id)
                .map(UserDetail -> {
                    UserDetail.setPhone(newUserDetail.getPhone());
                    UserDetail.setAddress(newUserDetail.getAddress());
                    UserDetail.setBirthday(newUserDetail.getBirthday());
                    UserDetail.setFirstname(newUserDetail.getFirstname());
                    UserDetail.setLastname(newUserDetail.getLastname());
                    UserDetail.setGmail(newUserDetail.getGmail());
                    UserDetail.setState(newUserDetail.getState());
                    UserDetail.setTypeMember(newUserDetail.getTypeMember());
                    return userDetailService.addUserDetail(UserDetail);
                }).orElseGet(() -> {
                    newUserDetail.setUserID(id);
                    return userDetailService.addUserDetail(newUserDetail);
                }));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update userdetail successfully",updateUserDetail)
        );
    }

    //Delete a UserDetail
    @DeleteMapping("/{id}")
    //this request is: http://localhost:8080/api/userdetail/{id}
    ResponseEntity<ResponseObject> deleteTypeProduct(@PathVariable String id){
        boolean exists = userDetailService.findUserDetailByID(id);
        if(exists){
            userDetailService.deleteUserDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete userdetail successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Can find userdetail to delete","")
        );
    }
}
