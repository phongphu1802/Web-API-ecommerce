
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs";
import { Account } from "../entity/account";

@Injectable({
    providedIn: 'root'
})
export class AccountService {
    constructor(private httpClient:HttpClient) { }
    getAccounts():Observable<any>{
      return this.httpClient.get("http://localhost:8080/api/account");
    }
    getAccountByID(id:string):Observable<any>{
      return this.httpClient.get<any>('http://localhost:8080/api/account/'+id);
    }

    addAccount(account:Account, uid:String): Observable<any>{
      return this.httpClient.post("http://localhost:8080/api/account/insert/"+uid,account);
    }

    getAccountByUser(user:String):Observable<any>{
      return this.httpClient.get("http://localhost:8080/api/account/email/"+user);
    }

    UpdateStateAccount(uid:String, state:number):Observable<any>{
      return this.httpClient.put("http://localhost:8080/api/account/"+uid+"/"+state,"");
    }

}