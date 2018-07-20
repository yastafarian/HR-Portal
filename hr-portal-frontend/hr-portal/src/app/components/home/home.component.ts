import { Component, OnInit } from '@angular/core';
import { AppService } from '../../services/app.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Demo';
  greeting = {};

  constructor(
    private app: AppService, 
    private http: HttpClient, 
    private router: Router) {
    http.get('http://localhost:9000').subscribe(data => this.greeting = data);
  }

  ngOnInit() {
    if (!this.authenticated())
      this.router.navigateByUrl('/login');
  }

  authenticated() {
    return this.app.authenticated;
  }

}
