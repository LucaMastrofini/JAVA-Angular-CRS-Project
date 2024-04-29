import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/model/course';
import { RegisteredCourse } from 'src/app/model/registered-course';
import { Student } from 'src/app/model/student';
import { CourseService } from 'src/app/services/student/course.service';
import { RegisteredCourseService } from 'src/app/services/student/registered-course.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view-applied-courses',
  templateUrl: './view-applied-courses.component.html',
  styleUrls: ['./view-applied-courses.component.scss']
})
export class ViewAppliedCoursesComponent implements OnInit {

  student: Student = new Student(0, "", "", "", false);

  myCourses: RegisteredCourse[] = [];

  constructor(private courseService: CourseService, public router: Router, private userService: UserService, private registeredCourseService: RegisteredCourseService) { }

  ngOnInit(): void {

    if (JSON.parse(this.userService.getData() as string).role != 3) {
      this.userService.deleteData();
      this.router.navigate(['']);
    } else {
      this.student = JSON.parse(this.userService.getData() as string)

      this.getAppliedCourse();
    }
  }

  getAppliedCourse() {

    this.registeredCourseService.getAppliedCourses(this.student).subscribe(data => {
      console.log(data);
      this.myCourses = data;

    })
    console.log("my courses array" + this.myCourses);

  }


  payCourse(myCourse: RegisteredCourse) {

    this.registeredCourseService.makePayment(this.student, myCourse.course)
      .subscribe(data => {
        this.getAppliedCourse();
        console.log(data)
      })

  }

  dropCourse(myCourse: RegisteredCourse) {

    this.registeredCourseService.dropCourse(this.student, myCourse.course)
      .subscribe(data => {
        this.getAppliedCourse();
        console.log(data)
        location.reload();
      })

  }

}
