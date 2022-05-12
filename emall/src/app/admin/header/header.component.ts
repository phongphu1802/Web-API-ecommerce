import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { UserDetail } from 'src/app/entity/user-detail';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  userdetail!: UserDetail;
  isLogged = false;
  uid!: any;
  constructor(private loginService:LoginService,
    private router:Router,
    private userService:UserService,) { }

  getUserDetail(id:string){
    this.userService.getUserByID(id).subscribe(Response=>{
      this.userdetail=Response['data'];
    })
  }

  ngOnInit(): void {
    if(sessionStorage.getItem("adminuserid")!=null){
      this.uid=sessionStorage.getItem("userid");
      this.getUserDetail(this.uid);
    }else{
      this.router.navigate(["/index"]);
    }
  }

  logout(){
    this.isLogged = this.loginService.logOut();
    return this.router.navigate(["/login"]);
  }

}
