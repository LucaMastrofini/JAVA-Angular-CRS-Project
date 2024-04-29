import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProfessorComponent } from './list-professor.component';

describe('ListProfessorComponent', () => {
  let component: ListProfessorComponent;
  let fixture: ComponentFixture<ListProfessorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListProfessorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListProfessorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
