import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Employee } from '../../models/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeApiService {


  constructor(
    private http: HttpClient
  ) { }

  getEmployeeById(employeeId: string): Observable<Employee>{
    return this.http
                .get('http://localhost:9000/employee?employeeId=' + employeeId).pipe(map(
                  (data: Employee) => {
                    return data;
                  }
                ));
  }

  editEmployee(employee: Employee): Observable<Employee> {
    return this.http
      .put('http://localhost:9000/edit_employee', employee).pipe(map(
        (newEmployee: Employee)=>{
          return newEmployee;
        }
      ));
  }

  deleteEmployee(empNo: Number): Observable<any> {
    return this.http
                .delete('http://localhost:9000/employee?employeeId=' + empNo).pipe(map(
                  (res) => {
                    console.log(res);
                  }
                ));
  }

  private handleError(error: Response | any){
    console.error('EmployeeApiService::handleError', error);
    return Observable.throw(error);
  }
}
