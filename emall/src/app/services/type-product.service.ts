import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TypeProduct } from '../entity/type-product';
import { Observable } from 'rxjs/internal/Observable';
@Injectable({
  providedIn: 'root'
})
export class TypeProductService {

  constructor(private httpClient:HttpClient) { }
  getTypeProducts():Observable<any>{
    return this.httpClient.get("http://localhost:8080/api/typeproduct");
  }
}