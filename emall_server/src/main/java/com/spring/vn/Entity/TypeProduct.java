package com.spring.vn.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Table(name = "typeproduct", schema="emall")
//Table typeproduct(typeProductID, typeName, state)
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeProductID;//Mã loại sản phẩm
    @Column(name = "typeName",columnDefinition = "nvarchar(50)",length = 50,nullable = false)
    private String typeName;//Tên loại sản phẩm
    @Column(name = "state", columnDefinition = "int",nullable = false)
    private int state;//Trạng thái
}
