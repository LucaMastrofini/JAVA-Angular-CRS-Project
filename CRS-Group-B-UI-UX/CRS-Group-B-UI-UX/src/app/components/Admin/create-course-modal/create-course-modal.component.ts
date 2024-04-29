import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { Course } from 'src/app/model/course';
import { Professor } from 'src/app/model/professor';
import { CourseService } from 'src/app/services/admin/course.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-create-course-modal',
  templateUrl: './create-course-modal.component.html',
  styleUrls: ['./create-course-modal.component.scss']
})
export class CreateCourseModalComponent implements OnInit {

  // courseArray:Array<Course> = new Array();

  // id:number = 1;

  // isFound:boolean = false;

  model: Course = new Course( 0, '', '', '', 0, new Professor(0, "", "", ""));

  getData: Course[] | undefined;

  constructor(private _httpService: CourseService, private userService: UserService, public router:Router) { }

  ngOnInit(): void {
    if (JSON.parse(this.userService.getData() as string).role != 1) {
      this.userService.deleteData();
      this.router.navigate(['']);
    } else {
      this.model = this._httpService.model;
    }
    
  }

  // createUpdateCourse() {

  //   this.courseArray.forEach(course => {
  //     if (this.model.courseID == course.courseID) {
  //       course.courseID = this.model.courseID;
  //       course.name = this.model.name;
  //       course.description = this.model.description;
  //       course.department = this.model.department;
  //       course.prereqID = this.model.prereqID;
  //       this.isFound = true;
  //     }

  //   });
  //   if (!this.isFound) {
  //     this.courseArray.push(new Course(this.id, this.model.name, this.model.description, this.model.department, this.model.professorID, this.model.prereqID));
  //     this.id++;
  //   }
  //   this.isFound = false;

  // }

  addCourse() {

    this._httpService.createCourse(this.model)
      .subscribe(data => {
        console.log(data);
        this.router.navigate(['admindashboard/listcourse']);
        this._httpService.model = new Course( 0, '', '', '', 0, new Professor(0, "", "", ""));
      })
    
  }

  updateCourse() {

    this._httpService.updateCourse(this.model)
      .subscribe(data => {
        console.log(data);
        this.router.navigate(['admindashboard/listcourse']);
        this._httpService.model = new Course( 0, '', '', '', 0, new Professor(0, "", "", ""));
      })
  }

  deleteCourse(course: Course) {

    this._httpService.deleteCourse(course)
      .subscribe(data => {
        console.log(data)
      })
  }
}
