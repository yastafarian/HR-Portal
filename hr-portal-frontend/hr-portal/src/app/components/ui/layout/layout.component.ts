import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

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

  constructor() { }

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

  onEmployee(){
    this.employeeClicked.emit();
  }

}
