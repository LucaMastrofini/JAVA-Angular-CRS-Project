import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Student } from 'src/app/model/student';


@Injectable({
  providedIn: 'root'
})
export class CourseService {

  url: string = "http://localhost:8093/";
  headers = new HttpHeaders().set('Content-Type', 'application/json').set('Access-Control-Allow-Origin', "*");

  constructor(private http: HttpClient) { }

  getCourses(): Observable<any> {
    return this.http.get(this.url + "viewAllCourses", { headers: this.headers });
  }

  getAppliedCourses(student: Student): Observable<any> {
    console.log(this.url+"viewAppliedCourses/"+student.studentID);
    return this.http.get(this.url + "viewAppliedCourses/" + student.studentID, { headers: this.headers });
  }

  



}
