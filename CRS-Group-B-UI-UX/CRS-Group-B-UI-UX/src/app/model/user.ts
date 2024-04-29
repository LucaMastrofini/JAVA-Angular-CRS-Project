export class User {
    public userID:number;
    public username:string;
    public password:string;
    public role:number;

    constructor(userID:number, username:string, password:string, role:number){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
