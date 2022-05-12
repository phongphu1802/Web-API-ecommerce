package com.spring.vn.Services;

import java.util.List;

import com.spring.vn.Entity.Payment;
import com.spring.vn.Repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    public Payment addPayment(Payment payment){
        return paymentRepository.save(payment);
    }
    public List<Payment> getAllPayment(){
        return paymentRepository.findAll();
    }
    public Payment findPaymentByID(long paymentID){
        return paymentRepository.findById(paymentID).get();
    }
}