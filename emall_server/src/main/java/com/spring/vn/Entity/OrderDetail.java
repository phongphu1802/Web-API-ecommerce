package com.spring.vn.Entity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Entity
@Table(name="orderdetail",schema="emall")
//Table orderdetail(orderID, productID, quantity, price, note)

public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oderDetailID;
    @ManyToOne
    @JoinColumn(name = "orderID",referencedColumnName = "orderID")
    private Order orderID;
    @ManyToOne
    @JoinColumn(name = "productID",referencedColumnName = "productID")
    private Product productID;//Mã sản phẩm
    @Column(name = "quantity")
    private int quantity;//Số lượng
    @Column(name = "price")
    private String price;//Tổng giá
    @Column(name = "note",columnDefinition = "nvarchar(255)", length = 200, nullable = false )
    private String note;//Thông tin sản phẩm
}
