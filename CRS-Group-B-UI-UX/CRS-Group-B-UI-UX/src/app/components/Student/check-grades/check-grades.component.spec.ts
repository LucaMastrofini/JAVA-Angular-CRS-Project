import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckGradesComponent } from './check-grades.component';

describe('CheckGradesComponent', () => {
  let component: CheckGradesComponent;
  let fixture: ComponentFixture<CheckGradesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckGradesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckGradesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
