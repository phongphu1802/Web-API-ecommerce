import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDetail } from 'src/app/entity/user-detail';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';
import { TypeProduct } from 'src/app/entity/type-product';
import { TypeProductService } from 'src/app/services/type-product.service';
import { CartService } from 'src/app/services/cart.service';
import { of } from 'rxjs/internal/observable/of';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLogged = false;
  isLoggedadmin = false;
  uid: any;
  adminuid!:string;
  userdetail!: UserDetail;
  typeproduct!: Array<TypeProduct>;
  cart!:Array<any>;
  sl:number = 0;
  total:number = 0;
  constructor( private loginService:LoginService,
    private router:Router,
    private userService:UserService,
    private typeproductService:TypeProductService,
    private cartService:CartService,) { }

  ngOnInit(): void {
    this.getTypeProduct();
    this.userdetail = new UserDetail();
    if(sessionStorage.getItem("userid")!=null){
      this.uid=sessionStorage.getItem('userid');
      this.getUserDetail(this.uid);
      this.getCartByUserDetail(this.uid);
    }
  }

  getUserDetail(id:string){
    this.userService.getUserByID(id).subscribe(Response=>{
      console.log(Response['data'])
      this.userdetail=Response['data'];
    })
  }

  getTotal(){
    this.cart?.forEach(element => {
      this.total += element.quantity*element['productID']['price'];
    }); 
  }

  isLoggedIn(){
    this.isLogged = this.loginService.isLogged();
    return this.isLogged;
  }

  isLoggedInAdmin(){
    this.isLoggedadmin = this.loginService.isLoggedadmin();
    return this.isLoggedadmin;
  }

  logout(){
    this.isLogged = this.loginService.logOut();
    return this.router.navigate(["/login"]);
  }

  getTypeProduct(){
    this.typeproductService.getTypeProducts().subscribe(Response=>{
        this.typeproduct = Response['data']
    })
  }

  getCartByUserDetail(id:string){
    this.cartService.getCartByUserDetail(id).subscribe(Response=>{
      this.cart = Response['data'];
      this.sl = this.cart.length
      this.getTotal();
    })
  }

  goDetail(id:String){
    this.router.navigate(['/product-detail'],{queryParams:{id}})
  }
}
