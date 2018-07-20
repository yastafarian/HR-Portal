import { Component, OnInit } from '@angular/core';
import { Employee } from '../../../models/employee';
import { EmployeeDataService } from '../../../services/data-services/employee-data.service';
import { DeptEmp } from '../../../models/dept-emp';
import { AppService } from '../../../services/app.service';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { EditEmployeeComponent } from '../dialogs/edit-employee/edit-employee.component';
import { DeleteEmployeeComponent } from '../dialogs/delete-employee/delete-employee.component';

@Component({
  selector: 'app-display-employee',
  templateUrl: './display-employee.component.html',
  styleUrls: ['./display-employee.component.css']
})
export class DisplayEmployeeComponent implements OnInit {

  constructor(
    private dataService: EmployeeDataService,
    private app: AppService,
    private router: Router,
    private dialog: MatDialog
  ) { }

  employee: Employee = new Employee();

  departments: DeptEmp[] = [];

  displayedColumns: string[] = ['name', 'fromDate', 'toDate'];

  emlpoyeeId: string = '';

  formattedSalary: String;

  formattedTitle: String;

  searchedId = '';

  found = false;

  warning = false;

  ngOnInit() {
    if (!this.app.authenticated)
      this.redirectToLogin();
  }

  getEmployee(id: string){
    this.dataService.getEmployeeById(id).subscribe(data => {
      if (data != null && data["id"] != null){
        this.employee = new Employee(data);

        this.employee.id = Number(this.emlpoyeeId);
        this.departments = this.employee.departments;

        if (this.employee.currentTitle === null){
          this.formattedTitle = "Left the company";
        }
        else {
          this.formattedTitle = this.employee.currentTitle;
        }

        if (this.employee.currentSalary === 0){
          this.formattedSalary = '---';
        }
        else{
          this.formattedSalary = '$' + this.employee.currentSalary.toLocaleString();
        }
        
        this.found = true;
        this.warning = false;
      }
      else{
        this.searchedId = this.emlpoyeeId;
        this.found = false;
        this.warning = true;;
      }
    })
  }

  redirectToLogin(){
    this.router.navigateByUrl('/login');
  }

  editEmployee(){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    let firstName = this.employee.firstName;
    let lastName = this.employee.lastName;

    dialogConfig.data = { firstName, lastName};
    
    const dialogRef = this.dialog.open(EditEmployeeComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
        data => {
          if(data['firstName'] && data['lastName']){
            this.employee.firstName = data['firstName'];
            this.employee.lastName = data['lastName'];
            console.log(this.employee);
            
            this.dataService.editEmployee(this.employee).subscribe(() => {
              this.search();
            });
          }
        }
    ); 
  }

  deleteEmployee(){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    let firstName = this.employee.firstName;
    let lastName = this.employee.lastName;

    dialogConfig.data = { firstName, lastName};
    
    const dialogRef = this.dialog.open(DeleteEmployeeComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
        data => {
          if(data['delete']){
            
            this.dataService.deleteEmployee(this.employee.id).subscribe(() => {
              this.router.navigateByUrl("/employee")
            });
            
          }
        }
    ); 
  }

  search(){
    this.getEmployee(this.emlpoyeeId);
  }

  onChangeEmployeeId(x){
    this.emlpoyeeId = x;
  }

  isFound(){
    return this.found;
  }

}
