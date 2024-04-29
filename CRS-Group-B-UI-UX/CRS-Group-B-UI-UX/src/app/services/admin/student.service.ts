import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from 'src/app/model/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {


  url: string = "http://localhost:8091/";

  headers = new HttpHeaders().set('Content-Type', 'application/json').set('Access-Control-Allow-Origin', "*")

  constructor(private httpClient: HttpClient) { }

  model:Student = new Student(0,"", "", "", false);

  getUnapprovedStudents(): Observable<any>{
    return this.httpClient.get(this.url + "viewUnapprovedStudents", { headers: this.headers });
  }

  confirmRegistration(student: Student): Observable<any> {

    const body = JSON.stringify(student);
    return this.httpClient.put(this.url + "approveRegistration/" + student.studentID, body, { headers: this.headers });

  }

  rejectRegistration(student: Student): Observable<any> {

    const body = JSON.stringify(student);
    let httpOptions = {};
    return this.httpClient.delete(this.url + "rejectRegistration/" + student.studentID, httpOptions);

  }


}
