import { Component, OnInit } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { Course } from 'src/app/model/course';

@Component({
  selector: 'app-create-course-modal',
  templateUrl: './create-course-modal.component.html',
  styleUrls: ['./create-course-modal.component.css']
})
export class CreateCourseModalComponent implements OnInit {

  courseArray:Array<Course> = new Array();

  id:number = 1;

  isFound:boolean = false;

  model:Course = new Course(0, '', '', '',0, 0);

  constructor(public modalRef: MdbModalRef<CreateCourseModalComponent>) {}

  ngOnInit(): void {
  }

  createUpdateCourse() {

    this.courseArray.forEach(course => {
      if (this.model.courseID == course.courseID) {
        course.courseID = this.model.courseID;
        course.name = this.model.name;
        course.description = this.model.description;
        course.department = this.model.department;
        course.prereqID = this.model.prereqID;
        this.isFound = true;
      }
      
    });
    if (!this.isFound) {
      this.courseArray.push(new Course(this.id, this.model.name, this.model.description, this.model.department, this.model.professorID, this.model.prereqID));
      this.id++;
    }
    this.isFound = false;

  }

}
