import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListCourseComponent } from './components/Admin/list-course/list-course.component';
import { CreateCourseModalComponent } from './components/Admin/create-course-modal/create-course-modal.component';
import { CreateProfessorModalComponent } from './components/Admin/create-professor-modal/create-professor-modal.component';
import { ListProfessorComponent } from './components/Admin/list-professor/list-professor.component';
import { ListUnregisteredStudentComponent } from './components/Admin/list-unregistered-student/list-unregistered-student.component';
import { MdbAccordionModule } from 'mdb-angular-ui-kit/accordion';
import { MdbCarouselModule } from 'mdb-angular-ui-kit/carousel';
import { MdbCheckboxModule } from 'mdb-angular-ui-kit/checkbox';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { MdbPopoverModule } from 'mdb-angular-ui-kit/popover';
import { MdbRadioModule } from 'mdb-angular-ui-kit/radio';
import { MdbRangeModule } from 'mdb-angular-ui-kit/range';
import { MdbRippleModule } from 'mdb-angular-ui-kit/ripple';
import { MdbScrollspyModule } from 'mdb-angular-ui-kit/scrollspy';
import { MdbTabsModule } from 'mdb-angular-ui-kit/tabs';
import { MdbTooltipModule } from 'mdb-angular-ui-kit/tooltip';
import { MdbValidationModule } from 'mdb-angular-ui-kit/validation';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ViewAppliedCoursesComponent } from './components/Student/view-applied-courses/view-applied-courses.component';
import { MakePaymentComponent } from './components/Student/make-payment/make-payment.component';
import { CheckGradesComponent } from './components/Student/check-grades/check-grades.component';
import { ViewAllCoursesComponent } from './components/Student/view-all-courses/view-all-courses.component';
import { ViewStudentsComponent } from './components/Professor/view-students/view-students.component';
import { ViewCoursesComponent } from './components/Professor/view-courses/view-courses.component';
import { LoginComponent } from './components/login/login.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { ProfessorDashboardComponent } from './components/professor-dashboard/professor-dashboard.component';
import { StudentDashboardComponent } from './components/student-dashboard/student-dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { CenterDirective } from './directives/center.directive';
import { StudentRegistrationComponent } from './components/student-registration/student-registration.component';
import { HomePageComponent } from './components/home-page/home-page.component';

@NgModule({
  declarations: [
    AppComponent,
    ListCourseComponent,
    CreateCourseModalComponent,
    CreateProfessorModalComponent,
    ListProfessorComponent,
    ListUnregisteredStudentComponent,
    ViewAppliedCoursesComponent,
    MakePaymentComponent,
    CheckGradesComponent,
    ViewAllCoursesComponent,
    ViewStudentsComponent,
    ViewCoursesComponent,
    LoginComponent,
    AdminDashboardComponent,
    ProfessorDashboardComponent,
    StudentDashboardComponent,
    HeaderComponent,
    FooterComponent,
    PageNotFoundComponent,
    CenterDirective,
    StudentRegistrationComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MdbAccordionModule,
    MdbCarouselModule,
    MdbCheckboxModule,
    MdbCollapseModule,
    MdbDropdownModule,
    MdbFormsModule,
    MdbModalModule,
    MdbPopoverModule,
    MdbRadioModule,
    MdbRangeModule,
    MdbRippleModule,
    MdbScrollspyModule,
    MdbTabsModule,
    MdbTooltipModule,
    MdbValidationModule,
    NoopAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
