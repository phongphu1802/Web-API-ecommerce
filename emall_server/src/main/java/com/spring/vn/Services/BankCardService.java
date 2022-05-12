package com.spring.vn.Services;

import java.util.List;

import com.spring.vn.Entity.BankCard;
import com.spring.vn.Repository.BankCardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class BankCardService {
    @Autowired
    BankCardRepository bankCardRepository;
    public List<BankCard> getAllABankCard(){
        return bankCardRepository.findAll();
    }
    public BankCard addBankCard(BankCard bankCard){
        return bankCardRepository.save(bankCard);
    }
    public BankCard findBankCardByID(Long bankID){
        return bankCardRepository.findById(bankID).get();
    }
    public BankCard findBankCardByBankNumber(String bankNumber){
        return bankCardRepository.findBankCardByBankNumber(bankNumber);
    }
    public long count(){
        return bankCardRepository.count();
    }
    public String deteleBankCard(Long bankID){
        BankCard bankCard = bankCardRepository.findById(bankID).get();
        bankCardRepository.delete(bankCard);
        return "Delete Bank Card Success";
    }
}
