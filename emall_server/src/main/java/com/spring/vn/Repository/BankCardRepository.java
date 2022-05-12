package com.spring.vn.Repository;

import com.spring.vn.Entity.Account;
import com.spring.vn.Entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard, Long> {
    public BankCard findBankCardByBankNumber(String bankNumber);
    long count();
}
