import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AppService } from '../../../services/app.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  title = "HR Portal"

  @Output()
  logoutClicked: EventEmitter<any> = new EventEmitter();

  @Output()
  loginClicked: EventEmitter<any> = new EventEmitter();

  @Output()
  homeClicked: EventEmitter<any> = new EventEmitter();

  @Output()
  departmentsClicked: EventEmitter<any> = new EventEmitter();

  @Output()
  employeeClicked: EventEmitter<any> = new EventEmitter();

  constructor(private app: AppService) { }

  ngOnInit() {
  }

  onLogout(){
    this.logoutClicked.emit();
  }

  onHome(){
    this.homeClicked.emit();
  }

  onLogin(){
    this.loginClicked.emit();
  }

  onDepartments(){
    this.departmentsClicked.emit();
  }

  onEmployee() {
    this.employeeClicked.emit();
  }

  authenticated() {
    return this.app.authenticated;
  }

}
