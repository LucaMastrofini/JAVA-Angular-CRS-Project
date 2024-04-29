import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../model/student';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url: string = "http://localhost:3001/";
  headers = new HttpHeaders().set('Content-Type', 'application/json').set('Access-Control-Allow-Origin', "*");


  constructor(private http: HttpClient) { }

  checkLogin(username: string, password: string, role: number): Observable<any> {
    let user: User = new User(0,username, password, role);
    return this.http.post(this.url + "login", user, { headers: this.headers });
  }

  studentRegister(username: string, password: string, name: string): Observable<any> {
    let student: Student = new Student(0, name, username, password, false);
    let temp: any;
    return this.http.post("http://localhost:8094/studentRegistration", student, { headers: this.headers});
  }

  studentRegistrationMongo(student: Student): Observable<any>{
    return this.http.post(this.url + "studentRegistration", student, { headers: this.headers });
  }
  saveData(user:any) {
    sessionStorage.setItem('globalUser', JSON.stringify(user));
  }

  getData() {
    return sessionStorage.getItem('globalUser');
  }

  deleteData() {
    sessionStorage.clear();
  }

}
