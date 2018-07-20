export class LoginForm {

    username: String;
    password: String;

    constructor(values: Object = {}){
        Object.assign(this, values);
    }
}
