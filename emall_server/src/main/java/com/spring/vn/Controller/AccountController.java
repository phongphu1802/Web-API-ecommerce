package com.spring.vn.Controller;

import java.util.List;
import java.util.Optional;

import com.spring.vn.Entity.Account;
import com.spring.vn.Entity.ResponseObject;
import com.spring.vn.Entity.UserDetail;
import com.spring.vn.Services.AccountService;
import com.spring.vn.Services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("")
        //this request is: http://localhost:8080/api/account
    ResponseEntity<ResponseObject> getAllAccount() {
        List<Account> account = accountService.getAllAccount();
        return account.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find account", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query account successfully", account)
                );
    }

    //Find By ID
    @GetMapping("/{id}")
    //this request is: http://localhost:8080/api/account/{id}
    ResponseEntity<ResponseObject> findByID(@PathVariable Long id) {
        Optional<Account> account = accountService.findAccountByID(id);
        return account.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query account successfully", account)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find account with id = " + id, "")
                );
    }

    //Find By User
    @GetMapping("email/{user}")
    //this request is: http://localhost:8080/api/account/email/{user}
    ResponseEntity<ResponseObject> findByUser(@PathVariable String user) {
        System.out.println(user);
        Account account = accountService.findAccountByEmail(user);
        System.out.println(account);
        if(account==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find account with email = " + user, "")
            );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query account successfully", account)
            );
        }
    }

    //Insert new Account with POST method
    @PostMapping("/insert/{uid}")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/account/insert/{uid}
    ResponseEntity<ResponseObject> insertAccount(@RequestBody Account newAccount, @PathVariable(name = "uid")String uid) {
        UserDetail user = userDetailService.getUserDetailID(uid).get();
        newAccount.setUserID(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert account successfully", accountService.addAccount(newAccount))
        );
    }

    //Login account
    @PostMapping("/login")
    @ResponseBody
    //this request is: http://localhost:8080/api/account/login
    ResponseEntity<ResponseObject> loginAccount(@RequestBody Account account) throws Exception {
        System.out.println(account.getEmail());
        System.out.println(account.getPassword());
        Account existAccount = null;
        if (account.getEmail() != null && account.getPassword() != null) {
            existAccount = accountService.findAccountByEmailAndPassword(account.getEmail(), account.getPassword());
            if (existAccount == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "login failed", "")
                );
            }
        }
        Account account1 = accountService.findAccountByEmail(account.getEmail());
        System.out.println(account1);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "login successfully", account1)
        );
    }

    //Update account state
    @PutMapping("/{id}/{state}")
        //Postman : Raw, JSON
        //Update, upset = update if found, otherwise insert
        //this request is: http://localhost:8080/api/account/{id}/{state}
    ResponseEntity<ResponseObject> updateUserDetail(@PathVariable long id, @PathVariable int state){
        Account updateAccount = accountService.findAccountByID(id).get();
        updateAccount.setState(state);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update account state successfully",accountService.addAccount(updateAccount))
        );
    }
}

