import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Course } from 'src/app/model/course';
import { Professor } from 'src/app/model/professor';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  url: string = "http://localhost:8091/";

  headers = new HttpHeaders().set('Content-Type', 'application/json').set('Access-Control-Allow-Origin', "*");

  constructor(private httpClient: HttpClient) { }

  model: Course = new Course(0, "", "", "", 0, new Professor(0, "", "", ""));


  getCourses(): Observable<any> {

    return this.httpClient.get(this.url + "viewAllCourses", { headers: this.headers });

  }

  createCourse(course: Course): Observable<any> {

    const body = JSON.stringify(course);
    return this.httpClient.post(this.url + "createCourse", body, { headers: this.headers });

  }

  updateCourse(course: Course): Observable<any> {

    const body = JSON.stringify(course);
    return this.httpClient.put(this.url + "updateCourse/" + course.courseID, body, { headers: this.headers });

  }

  deleteCourse(course: Course): Observable<any> {

    const body = JSON.stringify(course);
    let httpOptions = {};
    return this.httpClient.delete(this.url + "deleteCourse/" + course.courseID, httpOptions);

  }

  saveInfo(course: Course) {
    this.model = course;
  }

}


