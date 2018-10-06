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
    /*
      If there are credentials provided, then add an autherization header, otherwise
      the headers will be empty.
    */
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ":" + credentials.password)
    }: {});

    this.http.get('http://localhost:9000/user', {headers: headers}).subscribe(response => {
      if (response['name'] && response['authorities']){        
        this.setUpLocalStorage(response);
        this.authenticated = true;
      } 
      else {
        this.authenticated = false;
      }
      return callback && callback();
    });
  }

  setUpLocalStorage(response){
    let auths = [];
    for (var item in response['authorities']){
      auths.push(response['authorities'][item]['authority']);
    }
    localStorage.setItem('authorities', JSON.stringify(auths));
  }

  checkCanWrite(): boolean{
    let auths = JSON.parse(localStorage.getItem('authorities'));
    for (var index in auths){
      if (auths[index] === 'WRITE')
          return true;
    }
    return false;
  }
}
