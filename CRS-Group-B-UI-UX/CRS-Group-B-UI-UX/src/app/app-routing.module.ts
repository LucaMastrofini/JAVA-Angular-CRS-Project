import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateCourseModalComponent } from './components/Admin/create-course-modal/create-course-modal.component';
import { CreateProfessorModalComponent } from './components/Admin/create-professor-modal/create-professor-modal.component';
import { ListCourseComponent } from './components/Admin/list-course/list-course.component';
import { ListProfessorComponent } from './components/Admin/list-professor/list-professor.component';
import { ListUnregisteredStudentComponent } from './components/Admin/list-unregistered-student/list-unregistered-student.component';
import { LoginComponent } from './components/login/login.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { ViewCoursesComponent } from './components/Professor/view-courses/view-courses.component';
import { ViewStudentsComponent } from './components/Professor/view-students/view-students.component';
import { CheckGradesComponent } from './components/Student/check-grades/check-grades.component';
import { ViewAllCoursesComponent } from './components/Student/view-all-courses/view-all-courses.component';
import { ViewAppliedCoursesComponent } from './components/Student/view-applied-courses/view-applied-courses.component';
import { ProfessorDashboardComponent } from './components/professor-dashboard/professor-dashboard.component';
import { StudentDashboardComponent } from './components/student-dashboard/student-dashboard.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { AppComponent } from './app.component';
import { StudentRegistrationComponent } from './components/student-registration/student-registration.component';
import { HomePageComponent } from './components/home-page/home-page.component';

const routes: Routes = [

  {
    path: '', component: HomePageComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'studentregistration', component: StudentRegistrationComponent }
    ]
  },

  {
    path: 'admindashboard', component: AdminDashboardComponent,
    children: [
      { path: '', component: ListCourseComponent },
      { path: 'listcourse', component: ListCourseComponent },
      { path: 'courseform', component: CreateCourseModalComponent },
      { path: 'listprofessor', component: ListProfessorComponent },
      { path: 'professorform', component: CreateProfessorModalComponent },
      { path: 'listunregisteredstudent', component: ListUnregisteredStudentComponent }
    ]
  },

  {
    path: 'professordashboard', component: ProfessorDashboardComponent,
    children: [
      { path: '', component: ViewCoursesComponent },
      { path: 'viewcourses', component: ViewCoursesComponent },
      { path: 'viewstudents', component: ViewStudentsComponent }
    ]
  },

  {
    path: 'studentdashboard', component: StudentDashboardComponent,
    children: [
      { path: '', component: ViewAllCoursesComponent },
      { path: 'viewallcourses', component: ViewAllCoursesComponent },
      { path: 'viewappliedcourses', component: ViewAppliedCoursesComponent },
      { path: 'checkgrades', component: CheckGradesComponent }
    ]
  },
  
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
