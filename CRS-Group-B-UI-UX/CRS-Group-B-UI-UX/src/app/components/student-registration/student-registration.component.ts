import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Student } from 'src/app/model/student';
import { UserService } from 'src/app/services/user.service';


// import custom validator to validate that password and confirm password fields match
import { MustMatch } from '../../helpers/must-match.validator';

@Component({
  selector: 'app-student-registration',
  templateUrl: './student-registration.component.html',
  styleUrls: ['./student-registration.component.scss']
})
export class StudentRegistrationComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({});
  submitted = false;
  errors: any = [];

  constructor(private formBuilder: FormBuilder, private userService: UserService, public router: Router) { }

  ngOnInit(): void {

    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    }, {
      validator:
        [
          MustMatch('password', 'confirmPassword')
        ]
    });

  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    // display form values on success

    this.userService.studentRegister(this.registerForm.value.username, this.registerForm.value.password, this.registerForm.value.name).subscribe((data: any) => {
      data.password = this.registerForm.value.password;
      this.userService.studentRegistrationMongo(data).subscribe(result => {

      });
      this.errors = [];
      console.log('object created');
      this.router.navigate(['login']);
    },
      (error: any) => {
        this.errors = ({ usernameTaken: error.error.message });
        console.log(this.errors);
      });
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
    this.errors = [];
  }






}
