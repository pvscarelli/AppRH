import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobdetailsComponent } from './jobdetails.component';

describe('JobdetailsComponent', () => {
  let component: JobdetailsComponent;
  let fixture: ComponentFixture<JobdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobdetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(JobdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
