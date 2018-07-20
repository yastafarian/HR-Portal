import { Injectable } from '@angular/core';
import { EmployeeApiService } from '../api-services/employee-api.service';
import { Observable, of } from 'rxjs';
import { Employee } from '../../models/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeDataService {

  constructor(
    private api: EmployeeApiService
  ) { }

  getEmployeeById(employeeId: string): Observable<Employee>{
    return this.api.getEmployeeById(employeeId);
  }

  editEmployee(employee: Employee): Observable<Employee> {
    return this.api.editEmployee(employee);
  }

  deleteEmployee(empNo: Number): Observable<any> {
    return this.api.deleteEmployee(empNo);
  }
}
