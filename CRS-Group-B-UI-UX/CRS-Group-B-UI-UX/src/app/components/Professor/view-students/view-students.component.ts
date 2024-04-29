import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/model/course';
import { Grade } from 'src/app/model/grade';
import { Professor } from 'src/app/model/professor';
import { CourseService } from 'src/app/services/professor/course.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view-students',
  templateUrl: './view-students.component.html',
  styleUrls: ['./view-students.component.scss']
})
export class ViewStudentsComponent implements OnInit {

  professor: Professor = new Professor(2, "Amit", "Amit", "1234");
  grades: Grade[] = new Array();
  courses: Course[] = new Array();
  isEditable:boolean = false;
  editableId:number[] = new Array();
  model:number = -1;

  constructor(private courseService: CourseService, private userService: UserService, public router: Router) { }

  ngOnInit(): void {
    if (JSON.parse(this.userService.getData() as string).role != 2) {
      this.router.navigate(['']);
      this.userService.deleteData();
    }
    else {
      this.professor = JSON.parse(this.userService.getData() as string);
    this.getMyCourses();
    }  
  }

  getStudents(courseID:number){
    this.courseService.getMyStudents(courseID, this.professor).subscribe(data => {
      console.log(data);
      this.grades = data;
    })
  }

  editToggle(id:number, grade:number) {
    this.editableId.push(id);
    this.model = grade;
  }

  checkId(id:number): boolean {
    return this.editableId.includes(id);
  }

  saveGrade(grade:Grade) {
    this.courseService.recordGrade(grade.course, grade.student, this.model).subscribe(data => {
      console.log(data);
    });
    let index:number = this.editableId.indexOf(grade.student.studentID);
    this.editableId.splice(index, 1);
    this.getStudents(grade.course.courseID);
  }

  getMyCourses(){
    this.courseService.getMyCourses(this.professor).subscribe(data =>{
      console.log(data);
      this.courses = data;
    })
  }



  

}
