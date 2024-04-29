import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Course } from 'src/app/model/course';
import { Student } from 'src/app/model/student';

@Injectable({
  providedIn: 'root'
})
export class RegisteredCourseService {
  url: string = "http://localhost:8093/";
  headers = new HttpHeaders().set('Content-Type', 'application/json').set('Access-Control-Allow-Origin', "*");

  constructor(private http: HttpClient) { }


  getAppliedCourses(student: Student): Observable<any> {
    return this.http.get(this.url + "viewRegisteredCourses/" + student.studentID, { headers: this.headers });
  }

  addCourse(student: Student, course: Course): Observable<any> {
    const body: String = "{}";
    return this.http.post(this.url + "applyToCourseStudent/" + student.studentID + "/" + course.courseID, body, { headers: this.headers });
  }

  dropCourse(student: Student, course: Course): Observable<any> {
    return this.http.delete(this.url + "dropCourse/" + student.studentID + "/" + course.courseID, { headers: this.headers });
  }

  makePayment(student: Student, course: Course): Observable<any> {
    return this.http.put(this.url + "makePayment/" + student.studentID + "/" + course.courseID, { headers: this.headers });
  }

  checkGrades(student: Student): Observable<any> {
    return this.http.get(this.url + "checkGrades/" + student.studentID, { headers: this.headers });
  }
}
