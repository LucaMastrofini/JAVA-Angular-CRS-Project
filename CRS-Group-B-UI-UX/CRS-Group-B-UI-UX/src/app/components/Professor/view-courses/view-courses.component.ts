import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/model/course';
import { Professor } from 'src/app/model/professor';
import { CourseService } from 'src/app/services/professor/course.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view-courses',
  templateUrl: './view-courses.component.html',
  styleUrls: ['./view-courses.component.scss']
})
export class ViewCoursesComponent implements OnInit {

  professor: Professor = new Professor(0, "", "", "");

  model: Course = new Course(0, '', '', '', 0, new Professor(0, "", "", ""));

  getData: Course[] | undefined;

  constructor(private courseService: CourseService, private userService: UserService, public router: Router) { }

  ngOnInit(): void {
    if (JSON.parse(this.userService.getData() as string).role != 2) {
      this.router.navigate(['']);
      this.userService.deleteData();
    }
    else {
      this.professor = JSON.parse(this.userService.getData() as string);
      this.getCourses();
    }
    
  }

  getCourses() {

    this.courseService.getCourses().subscribe(data => {
      console.log(data);
      this.getData = data;
    })

  }

  applyToCourse(course:Course) {
    this.courseService.applyToCourse(course, this.professor).subscribe(data => {
      console.log(data);
    })
  }

}
