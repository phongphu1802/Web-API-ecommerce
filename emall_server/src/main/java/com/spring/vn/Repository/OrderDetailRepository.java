package com.spring.vn.Repository;
import java.util.List;

import com.spring.vn.Entity.*;

import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{
    public List<OrderDetail> findByOrderID(String orderID);
    @Query(value = "SELECT sum(ord.Total),Date(ord.endTime) from  orders ord where Month(ord.endTime)=?1 and ord.state='4'  group by Date(ord.endTime)",nativeQuery = true)
    public List<Object> findTotalItemGroupbyProductID(int thang);
    @Query(value = "SELECT sum(detail.quantity),p.product_name from order_detail detail join orders ord on detail.order_id = ord.orderid join product p on detail.product_id = p.pid where Month(ord.endTime)=?1 and ord.state=?2  group by detail.product_id",nativeQuery = true)
    public List<Object> statisticalMonth(int thang,int state);
    @Query(value="SELECT count(ord.orderid),Date(ord.startTime) from orders ord join order_detail detail on ord.orderid = detail.order_id where Date(ord.startTime)=?1",nativeQuery = true)
    public List<Object> statisticalDay(String date,int state);
    public List<OrderDetail> findAllByProductID(String productID);
}