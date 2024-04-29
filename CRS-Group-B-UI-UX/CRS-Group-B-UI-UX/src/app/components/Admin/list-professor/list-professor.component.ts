import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Professor } from 'src/app/model/professor';
import { ProfessorService } from 'src/app/services/admin/professor.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-list-professor',
  templateUrl: './list-professor.component.html',
  styleUrls: ['./list-professor.component.scss']
})
export class ListProfessorComponent implements OnInit {


  model: Professor = new Professor(0, "", "", "");
  getData: Professor[] | undefined;

  constructor(private httpService: ProfessorService, private userService: UserService, public router: Router) { }

  ngOnInit(): void {
    if (JSON.parse(this.userService.getData() as string).role != 1) {
      this.userService.deleteData();
      this.router.navigate(['']);
    } else {
      this.getProfessors()
    }
  }


  getProfessors() {
    this.httpService.getProfessors().subscribe(data => {
      console.log(data);
      this.getData = data;
    })
  }
}
