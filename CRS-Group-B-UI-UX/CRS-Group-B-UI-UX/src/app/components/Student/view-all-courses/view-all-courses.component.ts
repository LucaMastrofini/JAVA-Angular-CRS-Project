import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/model/course';
import { Student } from 'src/app/model/student';
import { CourseService } from 'src/app/services/student/course.service';
import { RegisteredCourseService } from 'src/app/services/student/registered-course.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view-all-courses',
  templateUrl: './view-all-courses.component.html',
  styleUrls: ['./view-all-courses.component.scss']
})
export class ViewAllCoursesComponent implements OnInit {

  student: Student = new Student(0, "", "", "", false);

  allCourses: Course[] | undefined;

  myCourses: Course[] = [];

  constructor(private courseService: CourseService, private registeredCourseService: RegisteredCourseService, private userService: UserService, public router: Router) { }

  ngOnInit(): void {
    if (JSON.parse(this.userService.getData() as string).role != 3) {
      this.userService.deleteData();
      this.router.navigate(['']);
    } else {
      this.student = JSON.parse(this.userService.getData() as string)
      this.getCourses();
      this.getAppliedCourse();
    }


  }

  getCourses() {

    this.courseService.getCourses().subscribe(data => {
      console.log("GetCourses DATA" + data);
      this.allCourses = data;
      console.log("all courses array" + this.allCourses);

    })
    // console.log("all courses array" + this.allCourses);
  }

  getAppliedCourse() {

    this.courseService.getAppliedCourses(this.student).subscribe(data => {
      console.log("applied course Data " + data);
      this.myCourses = data;

    })
    console.log("my courses array" + this.myCourses);

  }

  applyToCourse(course: Course) {

    this.registeredCourseService.addCourse(this.student, course).subscribe(data => {
      console.log(data);
      location.reload();
      // this.router.navigate(['viewallcourses'])
      // this.getCourses();
    })
    

  }

  isApplied(course: Course) {
    let output: boolean = false;
    this.myCourses.forEach(myCourse => {
      if (myCourse.courseID == course.courseID) {
        output = true;
      }

    })
    return output;
  }

  goToAppliedCourses() {

    this.router.navigate(['studentdashboard/viewappliedcourses'])

  }
}
