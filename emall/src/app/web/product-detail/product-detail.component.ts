import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { Cart } from 'src/app/entity/cart';
import { Product } from 'src/app/entity/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  productid!: string
  product!: any
  cart!:Cart
  userID!:any;
  typename!:String;
  productname!:String;
  price!:Number;
  SL:Number = 1;
  constructor(private activatedRoute: ActivatedRoute,
    private router:Router,
    private productService:ProductService,
    private userService:UserService,
    private cartService:CartService) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(data =>{
      this.productid = data['id'];
      // console.log(data)
      this.productService.updateViewsProduct(data['id']).subscribe(Response=>{
        console.log("Cập nhật views thành công views = "+Response['data']['views']);
      })
      this.getProductByID(this.productid);
    })
  }

  getProductByID(productID:String){
    this.productService.getProductByID(productID).subscribe(Response=>{
      this.product = Response['data']
      this.typename = Response['data']['typeProduct']['typeName']+"/"+Response['data']['images']
      this.productname = Response['data']['productName']
      this.price = Response['data']['price']
    })
  }

  addToCard(productID:String){
    if(sessionStorage.getItem("userid")!=null){
      this.userID = sessionStorage.getItem("userid");
      this.cartService.addToCard(productID,this.userID,this.SL.valueOf()).subscribe(Response=>{
        alert("Bạn thêm sản phẩm vào giỏ hàng thành công.")
      })
    }else{
      alert("Bạn cần đăng nhập để thêm sản phẩm vào giỏ hàng.")
    }
    window.location.reload();
  }

  addSL(){
    this.SL = this.SL.valueOf()+1;
  }
  subSL(){
    this.SL = this.SL.valueOf()-1;
    if(this.SL < 1)
    this.SL = 1
  }
}
