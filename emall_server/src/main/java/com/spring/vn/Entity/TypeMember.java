package com.spring.vn.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Entity

@Table(name = "typemember", schema="emall")
//Table typemember(typeMemberID, typeName, state)
public class TypeMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeMemberID;//Mã loại thành viên
    @Column(name = "typeName",columnDefinition = "nvarchar(50)",length = 50,nullable = false)
    private String typeName;//Tên loại thành viên
    @Column(name = "state", columnDefinition = "int",nullable = false)
    private int state;//Trạng thái
}
