import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
@Injectable({
  providedIn: 'root'
})
export class TypeMemberService {

  constructor(private httpClient:HttpClient) { }
  getTypeMembers():Observable<any>{
    return this.httpClient.get("http://localhost:8080/api/typemember");
  }
}