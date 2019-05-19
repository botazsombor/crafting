import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private hd: HttpHeaders;
  private authenticated = new BehaviorSubject<boolean>(false);
  constructor(private http: HttpClient) {}

  login(name: String, pwd: String) {
    const link = 'http://35.205.82.160:8080/api/user/elements';
    this.hd = new HttpHeaders({
      'Content-Type': 'application/json',
      Accept: 'application/json',
      Authorization: 'Basic ' + btoa(name + ':' + pwd)
    });
    console.log(this.hd);
    this.http.get(link, { headers: this.hd }).subscribe(
      data => {
        this.authenticated.next(true);
      },
      error => {
        this.authenticated.next(false);
        console.log('Error: ', error);
      }
    );
  }

  isAuthetnticated(): boolean {
    return this.authenticated.value;
  }
  getHeader() {
    return this.hd;
  }
}
