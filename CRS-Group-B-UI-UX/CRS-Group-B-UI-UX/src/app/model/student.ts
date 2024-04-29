import { User } from "./user";

export class Student extends User{

    public studentID: number;
    public name: string;
    public registrationApproved:boolean;

    constructor(id: number, name: string, username:string, password:string, registrationApproved:boolean) {
        super(id, username, password, 3);
        this.studentID = id;
        this.name = name;
        this.registrationApproved = registrationApproved;
    }
}
