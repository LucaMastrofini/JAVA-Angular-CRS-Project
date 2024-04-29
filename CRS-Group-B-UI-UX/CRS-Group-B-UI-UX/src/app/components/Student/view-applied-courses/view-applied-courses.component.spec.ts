import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAppliedCoursesComponent } from './view-applied-courses.component';

describe('ViewAppliedCoursesComponent', () => {
  let component: ViewAppliedCoursesComponent;
  let fixture: ComponentFixture<ViewAppliedCoursesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewAppliedCoursesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAppliedCoursesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
