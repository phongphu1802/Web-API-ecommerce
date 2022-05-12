package com.spring.vn.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.vn.Utils.StringPrefixedSequenceIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@Table(name = "userdetail", schema="emall")
//Table userdetail(userID, phone, firstname, lastname, address, birthday, gmail, timestamp, state, typeMember)
public class UserDetail {
    @Id
    @GenericGenerator(
            name = "uid_seq",
            strategy = "com.spring.vn.Utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "U_"),
                    @org.hibernate.annotations.Parameter(
                            name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    @GeneratedValue(generator = "uid_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="userID",updatable = false,nullable = false)
    private String userID;//Mã người dùng
    @Column(name = "phone",columnDefinition = "nvarchar(20)",length = 12)
    private String phone;//Số điện thoại
    @Column(name = "firstname",columnDefinition = "nvarchar(20)",length = 20)
    private String firstname;//họ
    @Column(name = "lastname",columnDefinition = "nvarchar(20)",length = 20)
    private String lastname;//Tên
    @Column(name = "address",columnDefinition = "nvarchar(100)",length = 100)
    private String address;//Địa chỉ
    @Column(name="birthday")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date birthday;//Ngày sinh
    @Column(name = "gmail",columnDefinition = "nvarchar(100)",length = 100)
    private String gmail;//Gmail
    @Column(name = "state",columnDefinition = "int")
    private int state;//Trạng thái
    @OneToOne
    @JoinColumn(name = "typeMemberID",referencedColumnName = "typeMemberID")
    private TypeMember TypeMember;
}
