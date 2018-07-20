import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginForm } from '../../models/auth/login-form';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService {

  constructor(
    private http : HttpClient
  ) { }

  login(form: LoginForm): Observable<any> {

    return this.http
                .post("http://localhost:9000/user", form)
                .pipe(map(
                  (response) => {
                    return response;
                  }
                ));
  }


  private handleError(error: Response | any){
    console.error('AuthApiService::handleError', error);
    return Observable.throw(error);
  }
}


