import { User } from "./user";

export class Admin extends User {
    public adminID:number;

    constructor(id:number, username:string, password:string){
        super(id, username, password, 1);
        this.adminID = id;
    }
}
