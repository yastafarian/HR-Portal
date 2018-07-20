export class DeptEmp {

    fromDate: Date;

    toDate: Date;

    name: String = '';
    
    constructor(values: Object = {}){
        Object.assign(this, values);
    }
}
