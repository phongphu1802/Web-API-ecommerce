import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { Product } from 'src/app/entity/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-typeproduct',
  templateUrl: './typeproduct.component.html',
  styleUrls: ['./typeproduct.component.css']
})
export class TypeproductComponent implements OnInit {
  typename!: String
  product!: Array<any>
  constructor(private activatedRoute: ActivatedRoute,
    private router:Router,
    private productService:ProductService) { }

  ngOnInit(): void {
    this.activatedRoute.data.pipe(take(1)).subscribe((data) => {
      this.typename = data['text'] // do something with the data
      this.getProductByTypeProduct(this.typename);
    });
  }
  
  goDetail(id:String){
    this.router.navigate(['/product-detail'],{queryParams:{id}})
  }

  getProductByTypeProduct(name:String){
    this.productService.getProductByTypeProduct(name).subscribe(Response=>{
      this.product = Response['data']
      // console.log(this.product)
    })
  }
}
