import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { LoginForm } from '../../models/auth/login-form';
import { AuthenticationService } from '../../services/auth-services/authentication.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AppService } from '../../services/app.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: LoginForm;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private snackBar: MatSnackBar,
    private app: AppService,
    private zone: NgZone
  ) { }

  ngOnInit() {
    this.form = new LoginForm();
  }

  login() {
    console.log(this.form);
    
    this.app.authenticate(this.form, () => {
      this.app.authenticated = true;
      this.router.navigateByUrl('/');
      this.snackBar.open('Logged in Successfully!', 'Dismiss', {
        duration: 3000
      });
    });
    return false; 
  }

}
