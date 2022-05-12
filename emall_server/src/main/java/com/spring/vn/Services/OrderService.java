package com.spring.vn.Services;

import java.util.List;
import java.util.Optional;

import com.spring.vn.Entity.*;
import com.spring.vn.Repository.OrderRepository;
import com.spring.vn.Repository.UserDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;

    public Order getOrderById(String orderID) throws Exception{
        Optional<Order> op = orderRepository.findById(orderID);
        if(op.isEmpty()){
            throw new Exception("Not found");
        }
        else{
            return  op.get();
        }
    }

    public List<Order> getAllOrderByStateAndUserDetail(int state,String userID){
        UserDetail userDetail = userDetailRepository.findById(userID).get();
        return orderRepository.findByStateAndUserDetail(state,userDetail);
    }

    public List<Order> getAllOrderByState(int state){
        return orderRepository.findByState(state);
    }
    public List<Order> getAllOrderByTVMua(String userID){
        UserDetail userDetail = userDetailRepository.findById(userID).get();
        return orderRepository.findByUserDetail(userDetail);
    }
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }
    public Order saveOrder(Order order){
        return orderRepository.save(order);

    }
}
