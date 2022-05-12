package com.spring.vn.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "cart",schema="emall")
//Table cart(cardId, productID, userDetail, quantity)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;//Mã giỏ hàng
    @ManyToOne
    @JoinColumn(name = "productID",referencedColumnName = "productID")
    private Product productID;//Sản phẩm
    @ManyToOne
    @JoinColumn(name = "userID",referencedColumnName = "userID")
    private UserDetail userID;//Mã người dùng
    private int quantity;//Số lượng
}

