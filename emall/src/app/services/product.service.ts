import { Injectable } from '@angular/core';
import { Product } from '../entity/product';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient:HttpClient) { }
  getProducts():Observable<any>{
    return this.httpClient.get("http://localhost:8080/api/product");
  }
  
  getProductByID(id:String){
    return this.httpClient.get<any>("http://localhost:8080/api/product/"+id);
  }

  getProductByTypeProduct(name:String):Observable<any>{
    return this.httpClient.get<Product[]>("http://localhost:8080/api/product/typeproduct/"+name)
  }

  updateViewsProduct(id:String):Observable<any>{
    return this.httpClient.put("http://localhost:8080/api/product/views/"+id,"");
  }

  updateStateProduct(uid:String, state:number):Observable<any>{
    return this.httpClient.put("http://localhost:8080/api/product/state/"+uid+"/"+state,"");
  }
}
