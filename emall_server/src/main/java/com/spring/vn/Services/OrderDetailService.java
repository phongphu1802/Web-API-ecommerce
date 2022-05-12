package com.spring.vn.Services;

import java.util.List;

import com.spring.vn.Entity.*;
import com.spring.vn.Repository.OrderDetailRepository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDetailService {
    private OrderDetailRepository orderDetailRepository;
    public List<OrderDetail> getAllOrderDetailByOrderID(String oderID){
        return orderDetailRepository.findByOrderID(oderID);
    }
    public List<OrderDetail> getAllOrder(){
        return orderDetailRepository.findAll();
    }
    public OrderDetail saveOrderDetail(OrderDetail orderDetail){
        return orderDetailRepository.save(orderDetail);
    }
    public List<Object> getTotal(int month){
        return orderDetailRepository.findTotalItemGroupbyProductID(month);
    }
    public List<Object> getStatisticalMonth(int month,int state){
        return orderDetailRepository.statisticalMonth(month,state);
    }
    public List<Object> getStatisticalDay(String date,int state){
        return orderDetailRepository.statisticalDay(date, state);
    }

}
