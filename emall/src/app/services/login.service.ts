import { Account } from '../entity/account';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient:HttpClient) { }

  login(account: Account): Observable<any>{ 
    return this.httpClient.post("http://localhost:8080/api/account/login",account);
  }
  
  isLogged(){
    let sesson = sessionStorage.getItem("userid");
    if(sesson == null || sesson ==''){
      return false;
    }
    else{
      return true;
    }
  }

  isLoggedadmin(){
    let sesson = sessionStorage.getItem("adminuserid");
    if(sesson == null || sesson ==''){
      return false;
    }
    else{
      return true;
    }
  }

  logOut(){
    sessionStorage.clear();
    return false;
  }
}
