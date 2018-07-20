import { Component, OnInit } from '@angular/core';
import { DepartmentApiService } from '../../../services/api-services/department-api.service';
import { Department } from '../../../models/department';
import { AppService } from '../../../services/app.service';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { CreateDepartmentComponent } from '../dialogs/create-department/create-department.component';
import { DepartmentDataService } from '../../../services/data-services/department-data.service';
import { ChangeManagerComponent } from '../dialogs/change-manager/change-manager.component';
import { DepartmentManager } from '../../../models/department-manager';


@Component({
  selector: 'app-departments',
  templateUrl: './departments.component.html',
  styleUrls: ['./departments.component.css']
})
export class DepartmentsComponent implements OnInit {

  departments: Department[] = [];
  displayedColumns: string[] = ['number', 'name', 'manager', 'action'];

  name = '';
  number = '';

  loading = true;

  constructor(
    private api: DepartmentApiService,
    private dataService: DepartmentDataService,
    private app: AppService,
    private router: Router,
    private dialog: MatDialog  ) { }

  ngOnInit() {
    if (this.app.authenticated)
      this.fetchData();
    else
      this.redirectToLogin();
  }

  createDepartment(){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    let name = '';
    let number = '';
    let currentManager = 0;

    dialogConfig.data = { name, number, currentManager};
    
    const dialogRef = this.dialog.open(CreateDepartmentComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
        data => {
          if(data['name'] && data['number'] && data['currentManager']){
            this.dataService.addDepartment(new Department(data)).subscribe(() => {
              this.fetchData();
            });
          }
        }
    );  
  }

  changeManager(department: Department){
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    let currentManagerId = '';
    let departmentName = department.name;

    dialogConfig.data = {currentManagerId, departmentName};
    
    const dialogRef = this.dialog.open(ChangeManagerComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
        data => {
          if(data['currentManagerId']){

            let changeRequest = {
              deptNo: department.number,
              deptManagerId: Number(data['currentManagerId'])
            }
            this.dataService.changeManager(new DepartmentManager(changeRequest)).subscribe(() => {
              this.fetchData();
            });
          }
        }
    ); 
  }

  redirectToLogin(){
    this.router.navigateByUrl('/login');
  }

  fetchData(){
    this.loading = true;
    this.api.getAllDepartments().subscribe(data => {
      this.loading = false;
      this.departments = data;
    });
  }

}
