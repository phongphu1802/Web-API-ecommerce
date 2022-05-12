import { Component, OnInit } from '@angular/core';
import { UserDetail } from 'src/app/entity/user-detail';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-myaccount',
  templateUrl: './myaccount.component.html',
  styleUrls: ['./myaccount.component.css']
})
export class MyaccountComponent implements OnInit {
  uid: any;
  userdetail!: UserDetail;
  constructor(private userService:UserService) { }

  getUserDetail(id:string){
    this.userService.getUserByID(id).subscribe(Response=>{
      this.userdetail=Response['data'];
    })
  }

  ngOnInit(): void {
    this.userdetail = new UserDetail();
    if(sessionStorage.getItem("userid")!=null){
      this.uid=this.uid=sessionStorage.getItem('userid');
      this.getUserDetail(this.uid);
    }
  }
}
