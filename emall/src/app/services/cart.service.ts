import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs";
import { Cart } from "../entity/cart";
@Injectable({
    providedIn: 'root'
})
export class CartService {
    constructor(private httpClient:HttpClient) { }
    getCartByUserDetail(id:string): Observable<any> {
        return this.httpClient.get("http://localhost:8080/api/cart/user/"+id)
    }
    
    addToCard(productID:String, userID:String, quantity:number){
        console.log('http://localhost:8080/api/cart/update/'+productID+'/'+userID+'/'+quantity)
        return this.httpClient.post('http://localhost:8080/api/cart/insert/'+productID+'/'+userID+'/'+quantity,null)
    }

    UpdatetoCard(productID:String, userID:String, quantity:number){
        console.log('http://localhost:8080/api/cart/update/'+productID+'/'+userID+'/'+quantity)
        return this.httpClient.post('http://localhost:8080/api/cart/update/'+productID+'/'+userID+'/'+quantity,null)
    }

    DeleteCard(productID:String, userID:String){
        return this.httpClient.delete("http://localhost:8080/api/cart/"+productID+'/'+userID)
    }
}  