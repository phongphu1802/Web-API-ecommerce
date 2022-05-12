package com.spring.vn.Entity;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="bankcard",schema="emall")
//Table bankcard(bankID, bankNumber, bankName, userDetail)
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankID")//Mã
    private long bankID;
    @Column(name = "bankNumber")
    private String bankNumber;//Mã thẻ
    @Column(name = "bankName")
    private String bankName;//Tên ngân hàng
    @OneToOne
    @JoinColumn(name = "userID",referencedColumnName = "userID")
    private UserDetail userID;//Mã người dùng
    private int state;//Trạng thái
}
