import { Injectable } from '@angular/core';
import { AuthApiService } from '../api-services/auth-api.service';
import { LoginForm } from '../../models/auth/login-form';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private authApi : AuthApiService
  ) { }

  login(form: LoginForm): Observable<any> {
    return this.authApi.login(form);
  }
}
