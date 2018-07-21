import { Component, NgZone } from '@angular/core';
import { AppService } from '../services/app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators'
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = "Human Resources Portal"

  constructor(
    private app: AppService, 
    private http: HttpClient, 
    private router: Router,
    public snackBar: MatSnackBar,
    private zone: NgZone){
      
    this.app.authenticate(undefined, undefined);
  }
  ngOnInit(){
    this.app.authenticated = this.isLoggedIn();
    localStorage.getItem
  }

  isLoggedIn(){
    return (localStorage.getItem('currentUser') != null);
  }

  //===== Nav bar actions ===//

  login(){
    this.router.navigateByUrl('/login');
  }

  home(){
    console.log('home clicked');
    this.router.navigateByUrl('/');
  }

  logout() {
    this.http.post('http://localhost:9000/logout', {}).pipe(finalize(() => {
      this.app.authenticated =  false;
      localStorage.clear();
      this.snackBar.open('Logged out Successfully!', 'Dismiss', {
        duration: 3000
      });
      this.router.navigateByUrl('/login');
    })).subscribe();
  }

  departments() {
    this.router.navigateByUrl('/departments');
  }

  employee() {
    this.router.navigateByUrl('/employee');
  }
}
