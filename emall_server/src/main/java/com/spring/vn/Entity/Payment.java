package com.spring.vn.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="payment", schema="emall")
//Table payment(paymentID, paymentName, state)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentID")//Mã hình thức
    private long paymentID;
    @Column(name = "paymentName")//Tên hình thức
    private String paymentName;
    private int state;//Trạng thái
}
