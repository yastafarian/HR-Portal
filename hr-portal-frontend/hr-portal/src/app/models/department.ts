export class Department {

    name: String = "";

    number: String = "";

    currentManager: String = "";

    constructor(values: Object = {}){
        Object.assign(this, values);
    }
}
