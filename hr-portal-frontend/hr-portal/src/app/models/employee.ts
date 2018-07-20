import { DeptEmp } from './dept-emp';

export class Employee {

    id: Number;
	
	firstName: String = '';
	
	lastName: String = '';
	
	birthDate: Date;

	gender: String = '';
	
    hireDate: String = '';

    currentSalary: Number;

    currentTitle: String;
    
    departments: DeptEmp[] = []

    constructor(values: Object = {}){
        Object.assign(this, values);
    }
}
