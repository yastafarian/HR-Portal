import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Department } from '../../models/department';
import { DepartmentApiService } from '../api-services/department-api.service';
import { finalize } from 'rxjs/operators';
import { DepartmentManager } from '../../models/department-manager';

@Injectable({
  providedIn: 'root'
})
export class DepartmentDataService {

  constructor(
    private api: DepartmentApiService
  ) { }

  getAllDepartments(): Observable<Department[]> {
    return this.api.getAllDepartments();
  }

  addDepartment(newDepartment: Department): Observable<any>{
    return this.api.postDepartment(newDepartment).pipe(finalize(
      ()=>{
        console.log('posting!');
        return;
      }
    ));
  }

  changeManager(departmentManager: DepartmentManager): Observable<any> {

    console.log('Changing Manager to ' + departmentManager.deptManagerId);

    return this.api.changeManager(departmentManager).pipe(finalize(
      ()=>{
        return;
      }
    ));
  }
}
