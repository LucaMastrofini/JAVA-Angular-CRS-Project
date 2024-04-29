import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Professor } from 'src/app/model/professor';
import { ProfessorService } from 'src/app/services/admin/professor.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-create-professor-modal',
  templateUrl: './create-professor-modal.component.html',
  styleUrls: ['./create-professor-modal.component.scss']
})
export class CreateProfessorModalComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({});
  errors: any = [];
  submitted = false;
  model: Professor = new Professor(0, "", "", "");

  constructor(private formBuilder: FormBuilder, private httpService: ProfessorService, private userService: UserService, public router: Router) { }

  ngOnInit(): void {
    if (JSON.parse(this.userService.getData() as string).role != 1) {
      this.userService.deleteData();
      this.router.navigate(['']);
    }
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get f() { return this.registerForm.controls; }


  createProfessor() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    this.httpService.addProfessor(new Professor(0, this.registerForm.value.name, this.registerForm.value.username, this.registerForm.value.password))
      .subscribe(data => {
        console.log(data);
        this.errors = [];
        this.router.navigate(['admindashboard/listprofessor']);
      }, error => {
        this.errors = ({ usernameTaken: error.error.message });
        console.log(this.errors);
      })

  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
    this.errors = [];
  }
}
