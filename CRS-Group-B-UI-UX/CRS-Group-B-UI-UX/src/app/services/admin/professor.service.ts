import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Professor } from 'src/app/model/professor';

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {

  port:number = 7002;

  url: string = "http://localhost:8091/";
  headers = new HttpHeaders().set('Content-Type', 'application/json').set('Access-Control-Allow-Origin', "*");

  constructor(private httpClient: HttpClient) { }

  getProfessors(): Observable<any>{
    return this.httpClient.get(this.url + "viewProfessors", { headers: this.headers });
  }

  addProfessor(professor: Professor) : Observable<any>{
    console.log(this.url+"createProfessor")
    const body = JSON.stringify(professor);
    return this.httpClient.post(this.url + "createProfessor", body, { headers: this.headers});
  }
}
