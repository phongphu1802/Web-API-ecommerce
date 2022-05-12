package com.spring.vn.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.spring.vn.Utils.StringPrefixedSequenceIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.json.JSONArray;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "product",schema="emall")
//Table product(productID, typeProduct, productName, images, info, price, quantity, percent_discount, special_from_time, special_to_time, likes, view, state)
public class Product{
    @Id
    @GenericGenerator(
            name = "p_seq",
            strategy = "com.spring.vn.Utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "P_"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    @GeneratedValue(generator = "p_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "productID",columnDefinition = "nvarchar(20)", length = 50, nullable = false )
    private String productID;//Mã sản phẩm
    @OneToOne
    @JoinColumn(name = "typeProductID", referencedColumnName = "typeProductID")
    private TypeProduct typeProduct;//Loại sản phẩm
    @Column(name = "productName",columnDefinition = "nvarchar(255)", length = 50, nullable = false )
    private String productName;//Tên sản phẩm
    @Column(name = "images",columnDefinition = "json")
    @Lob
    @Convert(converter = JsonConverter.class)
    @JsonProperty("images")
    @JsonRawValue
    private JSONArray images;
    @Column(name = "info",columnDefinition = "nvarchar(255)", length = 200, nullable = false )
    private String info;//Thông tin sản phẩm
    @Column(name = "price",columnDefinition = "double")
    private double price;//Giá
    @Column(name = "quantity",columnDefinition = "int")
    private int quantity;//Số lượng
    @Column(name = "percent_discount",columnDefinition = "int")
    private int percent_discount;//Tỉ lệ giảm giá
    @Column(name = "special_from_time")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date special_from_time;//Ngày bắt đầu giảm
    @Column(name = "special_to_time")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date special_to_time;//Ngày kết thúc giảm giá
    @Column(name = "likes",columnDefinition = "int")
    private int likes;
    @Column(name = "views",columnDefinition = "int")
    private int views;
    @Column(name = "state",columnDefinition = "int")
    private int state;
}
