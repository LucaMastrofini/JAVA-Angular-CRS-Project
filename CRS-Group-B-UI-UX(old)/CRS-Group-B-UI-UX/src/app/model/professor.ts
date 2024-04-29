import { User } from "./user";

export class Professor extends User {
    public professorID: number;
    public name: String;

    constructor(id: number, name: String, username:string, password:string) {
        super(id, username, password, 2);
        this.professorID = id;
        this.name = name;
    }
}
