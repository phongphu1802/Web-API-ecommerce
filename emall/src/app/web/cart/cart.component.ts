import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from 'src/app/entity/cart';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart!:Array<any>;
  uid: any;
  total:number = 0;
  constructor(private cartService:CartService,
    private router:Router) { }

  getTotal(){
    this.cart?.forEach(element => {
      this.total += element.quantity*element['productID']['price'];
    }); 
    // window.location.reload();
  }

  getCartByUserDetail(id:string){
    this.cartService.getCartByUserDetail(id).subscribe(Response=>{
      this.cart = Response['data'];
      this.getTotal()
      // console.log(Response['data'])
    })
  }

  ngOnInit(): void {
    if(sessionStorage.getItem("userid")!=null){
      this.uid=sessionStorage.getItem("userid");
      this.getCartByUserDetail(this.uid)
    }
  }

  goDetail(id:String){
    this.router.navigate(['/product-detail'],{queryParams:{id}})
  }

  UpSLCart(productID:String, productQuantity:number){
    this.cartService.UpdatetoCard(productID, this.uid, productQuantity+1).subscribe(Response=>{
      console.log(Response)
      window.location.reload();
    })
  }

  DownSLCart(productID:String, productQuantity:number){
    if(productQuantity>1){
      this.cartService.UpdatetoCard(productID, this.uid, productQuantity-1).subscribe(Response=>{
        console.log(Response)
        window.location.reload();
      })
    }else{
      if(confirm("Bạn muốn xóa sản phẩm này khỏi giỏ hàng")) {
        this.cartService.DeleteCard(productID, this.uid).subscribe(Response=>{
          console.log(Response)
          window.location.reload();
        })
      }
    }
  }
}
