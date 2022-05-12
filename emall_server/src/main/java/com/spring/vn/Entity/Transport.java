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
@Table(name = "transport",schema="emall")
//Table transport(transportID, transportName, transportFee, state)
public class Transport {
    @Id
    @Column(name = "transportID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transportID;//Mã nhà vẫn chuyển
    @Column(name = "transportName",columnDefinition = "nvarchar(50)",length = 50,nullable = false)
    private String transportName;//Tên nhà vận chuyển
    @Column(name = "transportFee",columnDefinition = "double",nullable = false)
    private Double transportFee;//Chi phí
    @Column(name = "state",columnDefinition = "int")
    private int state;//Trạng thái
}