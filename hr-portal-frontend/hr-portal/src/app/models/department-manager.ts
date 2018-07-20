export class DepartmentManager {

    deptNo: String = '';

    deptManagerId: Number;

    constructor(values: Object = {}){
        Object.assign(this, values);
    }
}
