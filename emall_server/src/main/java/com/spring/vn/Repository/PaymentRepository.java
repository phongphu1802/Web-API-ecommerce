package com.spring.vn.Repository;

import com.spring.vn.Entity.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
