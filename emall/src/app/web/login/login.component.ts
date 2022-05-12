import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Account } from 'src/app/entity/account';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { sha256 } from 'js-sha256';
import { JsonPipe } from '@angular/common';
import { UserDetail } from 'src/app/entity/user-detail';
import { AccountService } from 'src/app/services/account.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @ViewChild('loginForm')
  loginForm!: NgForm;
  @ViewChild('registeForm')
  registeForm!: NgForm;
  newAccount!: Account;
  newUserDetail!:UserDetail;
  account!: Account;
  date!:Date;
  constructor(private loginService: LoginService,
    private router:Router,
    private accountService:AccountService,
    private userdetailService:UserService,){}

  ngOnInit(): void {
    this.date = new Date;
  }

  onSubmit(){
    if(!this.loginForm.valid){
      console.log("Invalid data");
      alert("Invalid login")
      return;
    };
    let account = new Account();
    account.email = this.loginForm.value.email
    account.password = sha256(this.loginForm.value.password);
    this.loginService.login(account)
    .subscribe(Response =>{
      if(Response['data']['state']==0){
        alert("Tài khoản của bạn đã bị vô hiệu hóa")
      }else{
        alert("Đăng nhập thành công")
        if(Response['data']['userID']['typeMember']['typeName'] != 'Admin'){
          sessionStorage.setItem("userid",Response['data']['userID']['userID'])
        }else{
          sessionStorage.setItem("adminuserid",Response['data']['userID']['userID'])
          sessionStorage.setItem("userid",Response['data']['userID']['userID'])
        }
        window.location.reload();
      }
    },(error) => {
      alert("Đăng nhập không thành công")
      console.log("Đăng nhập không thành công");
    });
    this.router.navigate(['/']);
  }

  onSubmitRegister(){
    if(!this.registeForm.valid){
      console.log("Invalid data");
      alert("Invalid register")
      return;
    };

    this.newAccount = new Account();
    this.newUserDetail = new UserDetail();
    
    //Lấy thông tin account
    this.newAccount.email = this.registeForm.value.username;
    this.newAccount.password = sha256(this.registeForm.value.password1);
    this.newAccount.state = 1;
    //Lấy thông tin userdetail
    this.newUserDetail.id = "1";
    this.newUserDetail.address = this.registeForm.value.address;
    this.newUserDetail.firstname = this.registeForm.value.firstname;
    this.newUserDetail.lastname = this.registeForm.value.lastname;
    this.newUserDetail.gmail = this.registeForm.value.emailre;
    this.newUserDetail.phone = this.registeForm.value.number;
    //Tính ngày sinh
    const bdate = new Date(this.registeForm.value.birthday);
    let timeDiff = Math.abs(Date.now() - bdate.getTime());
    let age = Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
    if(age<20){
      console.log("Bạn phải trên 20 tuổi mới có thể đăng ký.");
      alert("Bạn phải trên 20 tuổi mới có thể đăng ký.")
      return;
    }else{
      this.newUserDetail.birthday = this.registeForm.value.birthday;
    }

    console.log(this.newAccount)
    console.log(this.newUserDetail)

    //Kiểm tra tài khoản
    this.accountService.getAccountByUser(this.newAccount.email).subscribe(Response=>{
      alert("Tài khoản đa đã được dùng để đăng ký.")
    },(error) => {
      //Kiểm tra số điện thoại
      this.userdetailService.addUserDetail(this.newUserDetail, 2).subscribe(Response=>{
        console.log(Response)
        //Insert dữ liệu vào account
        this.accountService.addAccount(this.newAccount,Response['data']['userID']).subscribe(Response=>{
          console.log(Response)
          alert("Thêm tài khoản mới thành công")
          window.location.reload();
          this.router.navigate(['/']);
        },(error) => {
          alert("Thêm tài khoản mới không thành công")
        })
      },(error) => {
        alert("Số điện thoại đã được dùng để đăng ký.")
      })
    })
  }
}
