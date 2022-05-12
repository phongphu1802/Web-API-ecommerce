import { Component, OnInit, ViewChild } from '@angular/core';
import { Product } from 'src/app/entity/product';
import { TypeProduct } from 'src/app/entity/type-product';
import { ProductService } from 'src/app/services/product.service';
import { TypeProductService } from 'src/app/services/type-product.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @ViewChild('addProduct')
  addProduct!: NgForm;

  files:Array<File>=[];
  imageName:Array<string>=[]
  imgURL:any;
  selectedFile!:File;
  isToggle:boolean = true;
  formModal: any;
  typeproduct!: Array<TypeProduct>;
  typeproductfind!: any;
  product!: Array<any>;
  newproduct!:Product;
  constructor(private typeproductService:TypeProductService,
    private productService:ProductService,
    ) { }

  ngOnInit(): void {
    this.getTypeProduct();
    this.getProduct();
  }

  getTypeProduct(){
    this.typeproductService.getTypeProducts().subscribe(Response=>{
        this.typeproduct = Response['data']
    })
  }

  getProduct(){
    this.productService.getProducts().subscribe(Response =>{
      // console.log(Response['data'])
      this.product = Response['data']
    })
  }

  getProductbyType(name:String){
    this.productService.getProductByTypeProduct(name).subscribe(Response =>{
      // console.log(Response['data'])
      this.product = Response['data']
    },(error) => {
      this.product = ['']
    });
  }

  public popup_addproduct(){
    this.isToggle = !this.isToggle
    console.log(this.isToggle);
  }

  public onFileChanged(event:any) {
    //Select File
    if (event.target.files && event.target.files[0]) {
      let filesAmount = event.target.files.length;
      for (let i = 0; i < filesAmount; i++) {   
                  let k:File=event.target.files[i];
                 this.files.push(k); 
      }
      console.log(this.files)
    }
    
    this.selectedFile = event.target.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(this.selectedFile)
    reader.onload = (event2)=>{
      this.imgURL = reader.result
      // console.log(this.imgURL)
    }
  }

  addProductToDatabase(){
    if(!this.addProduct.valid){
      console.log("Invalid data");
      alert("Invalid login")
      return;
    };
    this.typeproductfind = new TypeProduct();
    this.newproduct = new Product();
    const uploadData = new FormData();

    this.newproduct.productname = this.addProduct.value.productname;
    this.newproduct.info = this.addProduct.value.info;
    this.newproduct.price = this.addProduct.value.price;
    this.newproduct.quantity = this.addProduct.value.quantity;
    this.typeproductfind = this.typeproduct.find(x=>x.typeID ===  this.addProduct.value.typeproduct);
    this.newproduct.typeproduct = this.typeproductfind;
    this.newproduct.state = 1;
    this.newproduct.views =1;

    //Lấy hình ảnh
    let date= new Date();
    const day=date.getDate();
    const month = date.getMonth()+1;
    const year = date.getFullYear();
    const hour = date.getHours();
    const minutes = date.getMinutes();
    const second = date.getSeconds();
    let i=1;
    if(this.files.length!=0){
      this.files.forEach(e=>{
        // console.log(e.type)
        let imgName=year+"_"+month+"_"+day+"_"+hour+"_"+minutes+"_"+second+"_"+i;
        uploadData.append('imageFile',e, imgName+".png");
        this.imageName.push(imgName+".png")
        i++;
      })
    }
    this.newproduct.images = this.imageName;
    console.log(this.imageName)

    console.log(this.newproduct)
  }
  
  //Update state
  UpdateStateClock(pid:string){
    this.productService.updateStateProduct(pid,0).subscribe(Response=>{
      alert("Khóa sản phẩm thành công.")
      window.location.reload();
    },(error) => {
      alert("Khóa sản phẩm không thành công.")
    })
  }

  UpdateStateUnClock(pid:String){
    this.productService.updateStateProduct(pid,1).subscribe(Response=>{
      alert("Mở khóa sản phẩm thành công.")
      window.location.reload();
    },(error) => {
      alert("Mở khóa sản phẩm không thành công.")
    })
  }
}
