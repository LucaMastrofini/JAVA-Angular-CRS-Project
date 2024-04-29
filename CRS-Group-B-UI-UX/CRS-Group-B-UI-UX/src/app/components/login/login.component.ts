import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({});
  submitted = false;
  errors: any = [];

  model: User = new User(0, "", "", 0);

  constructor(private formBuilder: FormBuilder, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      role: ['', Validators.required]
    });
    
  }

  get f() { return this.registerForm.controls; }

  // Logic for Login user 

  loginUser() {

    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    
    this.model = new User(0, this.registerForm.value.username, this.registerForm.value.password, this.registerForm.value.role);
    console.log(this.errors);
    console.log(this.model);

    if (this.model.role == 1) {
      this.userService.checkLogin(this.model.username, this.model.password, this.model.role).subscribe(data => {
        this.userService.saveData(data);
        this.router.navigate(['admindashboard']); // { skipLocationChange: true }
      }, error => {
        this.errors = ({ loginFail: error.error });
        console.log(this.errors);
      });
    }
    else if (this.model.role == 2) {
      this.userService.checkLogin(this.model.username, this.model.password, this.model.role).subscribe(data => {
        this.userService.saveData(data);
        this.router.navigate(['professordashboard']);
      }, error => {
        this.errors = ({ loginFail: error.error });;
        console.log(this.errors);
      });
    }
    else if (this.model.role == 3) {
      this.userService.checkLogin(this.model.username, this.model.password, this.model.role).subscribe(data => {
        this.userService.saveData(data);
        console.log("not working");
        this.router.navigate(['studentdashboard']);

      }, error => {
        this.errors = ({ loginFail: error.error });
        console.log(this.errors);
      });
    }

  }

}


