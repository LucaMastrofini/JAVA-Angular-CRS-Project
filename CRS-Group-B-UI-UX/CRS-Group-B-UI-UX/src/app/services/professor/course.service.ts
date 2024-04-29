import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/app/model/course';
import { Professor } from 'src/app/model/professor';
import { Student } from 'src/app/model/student';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  url: string = "http://localhost:8092/";
  headers = new HttpHeaders().set('Content-Type', 'application/json').set('Access-Control-Allow-Origin', "*");

  constructor(private http: HttpClient) { }

  getCourses(): Observable<any> {
    return this.http.get(this.url + "viewAllCourses", { headers: this.headers });
  }

  applyToCourse(course: Course, professor: Professor): Observable<any> {
    return this.http.put(this.url + "applyToCourseProfessor/" + course.courseID + "/" + professor.professorID, { headers: this.headers });
  }

  getMyCourses(professor: Professor): Observable<any> {
    return this.http.get(this.url + "viewMyCourses/" + professor.professorID, { headers: this.headers });
  }

  getMyStudents(courseID: number, professor: Professor): Observable<any> {
    return this.http.get(this.url + "viewStudents/" + courseID + "/" + professor.professorID, { headers: this.headers });
  }

  recordGrade(course: Course, student: Student, grade: number): Observable<any> {
    return this.http.put(this.url + "recordGrade/" + course.courseID + "/" + student.studentID + "/" + grade, { headers: this.headers });
  }
}
