import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListUnregisteredStudentComponent } from './list-unregistered-student.component';

describe('ListUnregisteredStudentComponent', () => {
  let component: ListUnregisteredStudentComponent;
  let fixture: ComponentFixture<ListUnregisteredStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListUnregisteredStudentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListUnregisteredStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
