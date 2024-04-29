import { Course } from "./course";
import { Student } from "./student";

export class Grade {
    public grade: number;
    public gradeLetter: String;
    public student: Student;
    public course: Course;

    constructor(grade: number, gradeLetter: String, student: Student, course: Course) {
        this.grade = grade;
        this.gradeLetter = gradeLetter;
        this.student = student;
        this.course = course;
    }
}
