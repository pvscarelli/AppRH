import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobformComponent } from './jobform.component';

describe('JobformComponent', () => {
  let component: JobformComponent;
  let fixture: ComponentFixture<JobformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobformComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(JobformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
