import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateProfessorModalComponent } from './create-professor-modal.component';

describe('CreateProfessorModalComponent', () => {
  let component: CreateProfessorModalComponent;
  let fixture: ComponentFixture<CreateProfessorModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateProfessorModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateProfessorModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
