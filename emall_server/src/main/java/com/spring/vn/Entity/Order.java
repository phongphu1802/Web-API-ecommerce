package com.spring.vn.Entity;

import com.spring.vn.Utils.StringPrefixedSequenceIdGenerator;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Table(name = "orders",schema="seller_store")
//Table oders(orderID, userDetail, deliveryAddress, destination, startTime, endTime, total, state, paymentID, transportID)
public class Order {
    @Id
    @GenericGenerator(
            name = "order_seq",
            strategy = "com.spring.vn.Utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "Or_"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    @GeneratedValue(generator = "order_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "orderID",columnDefinition = "nvarchar(20)",nullable = false)
    private String orderID;//Mã hóa đơn
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private UserDetail userDetail;//Mã người dùng
    @Column(name = "deliveryAddress",columnDefinition = "nvarchar(255)",nullable = false)
    private String deliveryAddress;//Nơi gửi
    @Column(name = "destination",columnDefinition = "nvarchar(255)",nullable = false)
    private String destination;//Nơi nhận
    @Column(name="starttime", nullable = false)
    private String startTime;//Ngày mua
    @Column(name="endtime", nullable = true, updatable = true, insertable = true)
    private String endTime;//Ngày nhận hàng
    @Column(name = "total",columnDefinition = "double",nullable = false)
    private Double total;//Tổng
    @Column(name = "state",columnDefinition = "int",nullable = false)
    private int state;//Trạng thái
    @OneToOne
    @JoinColumn(name = "paymentID", referencedColumnName = "paymentID")
    private Payment paymentID;//Hình thức thanh toán
    @OneToOne
    @JoinColumn(name = "transportID", referencedColumnName = "transportID")
    private Transport transportID;//Nhà vận chuyển
}

