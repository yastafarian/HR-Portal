import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
//import { Certificate } from 'crypto';

@Injectable({
  providedIn: 'root'
})

export class AppService {

  authenticated =  false;

  constructor(private http: HttpClient) { }

  authenticate(credentials, callback){
    console.log(AppService.name +'::: ' + 'authenticate() called.');

    /*
      If there are credentials provided, then add an autherization header, otherwise
      the headers will be empty.
    */
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ":" + credentials.password)
    }: {});

    this.http.get('http://localhost:9000/user', {headers: headers}).subscribe(response => {
      if (response['name']){
        this.authenticated = true;
      } else {
        this.authenticated = false;
      }
      return callback && callback();
    });
  }
}
