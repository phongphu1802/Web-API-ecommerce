package com.spring.vn.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Table(name = "account",schema="emall")
//Table account(accountID, email, password, state, userDetail)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountID;//Mã tài khoản
    @Column(name = "email",columnDefinition = "nvarchar(100)",length = 100,nullable = false)
    private String email;//Gmail
    @Column(name = "passwd",columnDefinition = "nvarchar(100)",length = 100,nullable = false)
    private String password;//Mật khẩu
    @Column(name = "state",columnDefinition = "int",nullable = false)
    private int state;//Trạng thái
    @OneToOne
    @JoinColumn(name = "userID",referencedColumnName = "userID")
    private UserDetail userID;//Mã người dùng
    public Account(String email,String password){
        this.email=email;
        this.password=password;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
}
