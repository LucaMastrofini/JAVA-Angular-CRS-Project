import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/model/student';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  studentArray:Student[] = new Array();

  

  constructor() { 
    let s1:Student = new Student(1, "Luca", "Lucam", "1234", false);
    let s2:Student = new Student(2, "Seb", "Seba", "1234", false);
    let s3:Student = new Student(3, "Nikhil", "Nik", "1234", false);
    this.studentArray.push(s1,s2,s3);
    console.log(JSON.stringify(this.studentArray));
  }

  ngOnInit(): void {
  }

  studentRegistration(student:Student) {
    console.log(JSON.stringify(student));
    let index = this.studentArray.indexOf(student);
    console.log(index);
    console.log(JSON.stringify(this.studentArray[index]));
    if (index != -1) {
        this.studentArray[index].isRegistered = true;
    }
  }

  init() {
    
  }

}
