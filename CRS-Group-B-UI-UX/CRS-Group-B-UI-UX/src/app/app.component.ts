import { Component } from '@angular/core';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { CreateCourseModalComponent } from './components/Admin/create-course-modal/create-course-modal.component';
import { Course } from './model/course';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  modalRef: MdbModalRef<CreateCourseModalComponent> | null = null;
  
  constructor(private modalService: MdbModalService) {}
  
  
  title = 'CRS-Group-B-UI-UX';

  openModal() {
    this.modalRef = this.modalService.open(CreateCourseModalComponent);
  }
}
