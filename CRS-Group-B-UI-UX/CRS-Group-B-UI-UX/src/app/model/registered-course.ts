import { Course } from "./course";
import { Student } from "./student";

export class RegisteredCourse {
    public course: Course;
    public student: Student;
    public feePaid: boolean;
    public grade: number;

    constructor(course: Course, student: Student){
        this.course = course;
        this.student = student;
        this.feePaid = false;
        this.grade = -1;
    }
}
