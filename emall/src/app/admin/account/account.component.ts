import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Account } from 'src/app/entity/account';
import { UserDetail } from 'src/app/entity/user-detail';
import { AccountService } from 'src/app/services/account.service';
import { TypeMemberService } from 'src/app/services/type-member.service';
import { UserService } from 'src/app/services/user.service';
import { sha256 } from 'js-sha256';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  @ViewChild('addAccount')
  addAccount!: NgForm;
  
  repairAccount!:any;
  typememberfind!: any;
  isToggle:boolean = true;
  isTogglere:boolean = true;
  account!:Array<any>;
  newAccount!:Account;
  newUserDetail!:UserDetail;
  typemember!:Array<any>;
  date!:Date;

  constructor(private accountService:AccountService,
   private userdetailService:UserService,
   private typememberService:TypeMemberService,
   private formBuilder:FormBuilder) { }

  reAccount= this.formBuilder.group({
    refirstname:['',Validators.required],
    relastname:['',Validators.required],
    remail:['',Validators.required],
    renumber:['',Validators.required],
    rebirthday:['',Validators.required],
    readdress:['',Validators.required],
    reusername:['',Validators.required],
    repassword1:['',Validators.required],
    retypemember:['',Validators.required],
  })

  ngOnInit(): void {
    this.date = new Date;
    this.getAllAccount();
    this.getAllTypeMember();
  }
  //------------------------------------------------------------------------------------------------------------------------
  //Lấy toàn bộ account
  getAllAccount(){
    this.accountService.getAccounts().subscribe(Response => {
      this.account = Response['data'];
    })
  }
  //------------------------------------------------------------------------------------------------------------------------
  //Lấy toàn bộ loại thành viên
  getAllTypeMember(){
    this.typememberService.getTypeMembers().subscribe(Response =>{
      this.typemember = Response['data'];
    })
  }
  //------------------------------------------------------------------------------------------------------------------------
  //Tạo tài khoản mới
  insertAccount(){
    if(!this.addAccount.valid){
      console.log("Invalid data");
      alert("Invalid register")
      return;
    };

    this.newAccount = new Account();
    this.newUserDetail = new UserDetail();
    
    //Lấy thông tin account
    this.newAccount.email = this.addAccount.value.username;
    this.newAccount.password = sha256(this.addAccount.value.password1);
    this.newAccount.state = 1;

    //Lấy thông tin userDetail
    this.newUserDetail.id = "1";
    this.newUserDetail.address = this.addAccount.value.address;
    //Tính ngày sinh
    const bdate = new Date(this.addAccount.value.birthday);
    let timeDiff = Math.abs(Date.now() - bdate.getTime());
    let age = Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
    if(age<20){
      console.log("Bạn phải trên 20 tuổi mới có thể đăng ký.");
      alert("Bạn phải trên 20 tuổi mới có thể đăng ký.")
      return;
    }else{
      this.newUserDetail.birthday = this.addAccount.value.birthday;
    }

    this.newUserDetail.firstname = this.addAccount.value.firstname;
    this.newUserDetail.lastname = this.addAccount.value.lastname;
    this.newUserDetail.phone = this.addAccount.value.number;
    this.newUserDetail.gmail = this.addAccount.value.email;
    this.newUserDetail.state = 1;
    this.typememberfind = this.typemember.find(x=>x.typeID ===  this.addAccount.value.typemember);

    //Kiểm tra tài khoản
    this.accountService.getAccountByUser(this.newAccount.email).subscribe(Response=>{
      alert("Tài khoản đa đã được dùng để đăng ký.")
    },(error) => {
      //Kiểm tra số điện thoại
      this.userdetailService.addUserDetail(this.newUserDetail, this.typememberfind['typeMemberID']).subscribe(Response=>{
        console.log(Response)
        //Insert dữ liệu vào account
        this.accountService.addAccount(this.newAccount,Response['data']['userID']).subscribe(Response=>{
          console.log(Response)
          alert("Thêm tài khoản mới thành công")
          window.location.reload();
        },(error) => {
          alert("Thêm tài khoản mới không thành công")
        })
      },(error) => {
        alert("Số điện thoại đã được dùng để đăng ký.")
      })
    })
  }
  //------------------------------------------------------------------------------------------------------------------------
  //Cập nhật tài khoản
  updateAccount(data:any){
    if(!this.reAccount.valid){
      alert("Dữ liệu thay đổi không được rỗng")
    }
    console.log(data)

  }
  //------------------------------------------------------------------------------------------------------------------------
  //Ẩn hiện popup
  public popup_addaccount(){
    this.isToggle = !this.isToggle
  }

  public popup_repairaccount(aid:number){
    this.isTogglere = !this.isTogglere
    this.repairAccount = this.account.find(x=>x.accountID === aid)
    let contact = {
      refirstname:this.repairAccount['userID']['firstname'],
      relastname:this.repairAccount['userID']['lastname'],
      remail:this.repairAccount['userID']['gmail'],
      renumber:this.repairAccount['userID']['phone'],
      rebirthday:this.repairAccount['userID']['birthday'],
      readdress:this.repairAccount['userID']['address'],
      reusername:this.repairAccount['email'],
      repassword1:this.repairAccount['password'],
      retypemember:this.repairAccount['userID']['typeMember']['typeName'],
    };
    this.reAccount.setValue(contact);
  }
  //------------------------------------------------------------------------------------------------------------------------
  //Cập nhật trạng thái
  UpdateStateClock(aid:string){
    this.accountService.UpdateStateAccount(aid,0).subscribe(Response=>{
      alert("Khóa tài khoản thành công.")
      window.location.reload();
    },(error) => {
      alert("Khóa tài khoản không thành công.")
    })
  }

  UpdateStateUnClock(aid:String){
    this.accountService.UpdateStateAccount(aid,1).subscribe(Response=>{
      alert("Mở khóa tài khoản thành công.")
      window.location.reload();
    },(error) => {
      alert("Mở khóa tài khoản không thành công.")
    })
  }
}
