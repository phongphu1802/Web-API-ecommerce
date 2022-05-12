package com.spring.vn.Services;
import java.util.List;
import java.util.Optional;

import com.spring.vn.Entity.Account;
import com.spring.vn.Repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    public List<Account> getAllAccount(){
        return accountRepository.findAll();
    }
    public Account addAccount(Account account){
        return accountRepository.save(account);
    }
    public Optional<Account> findAccountByID(Long id){
        return accountRepository.findById(id);
    }
    public Account findAccountByEmail(String email){
        return accountRepository.findAccountByEmail(email);
    }
    public Account findAccountByEmailAndPassword(String email,String password) throws Exception{
        return accountRepository.findAccountByEmailAndPassword(email,password);
    }
    public long count(){
        return accountRepository.count();
    }
    public String deteleAccount(Long accountID){
        Account account = accountRepository.findById(accountID).get();
        accountRepository.delete(account);
        return "Delete Account Success";
    }
}
