import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListjobsComponent } from './listjobs.component';

describe('ListjobsComponent', () => {
  let component: ListjobsComponent;
  let fixture: ComponentFixture<ListjobsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListjobsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListjobsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
