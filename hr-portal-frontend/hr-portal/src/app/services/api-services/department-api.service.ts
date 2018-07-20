import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { finalize } from 'rxjs/operators'
import { Department } from '../../models/department';
import { map} from 'rxjs/operators';
import { DepartmentManager } from '../../models/department-manager';

@Injectable({
  providedIn: 'root'
})
export class DepartmentApiService {


  constructor(
    private http: HttpClient
  ) { }

  postDepartment(newDepartment: Department): Observable<any> {
    return this.http
                .post('http://localhost:9000/departments', newDepartment).pipe(finalize(
                  ()=>{
                    console.log('posting!');
                    return;
                  }
                ));
  }

  changeManager(departmentManager: DepartmentManager): Observable<any> {
    console.log(departmentManager["deptManagerId"]);
    return this.http
                .put('http://localhost:9000/departments', departmentManager).pipe(finalize(
                  ()=>{
                    console.log('putting!');
                    return;
                  }
                ));
  }

  getAllDepartments(): Observable<Department[]> {
    return this.http
                .get('http://localhost:9000/departments').pipe(map(
                  (data: Department[]) => {
                    return data;
                  }
                ));
  }
}
