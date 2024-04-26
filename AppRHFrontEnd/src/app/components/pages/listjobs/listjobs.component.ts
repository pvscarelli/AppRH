import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { JobserviceService } from '../../../services/jobservice.service';
import { RouterLink } from '@angular/router';
import { Job } from '../../../models/job';

@Component({
  selector: 'app-listjobs',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './listjobs.component.html',
  styleUrl: './listjobs.component.scss',
})
export class ListjobsComponent implements OnInit {
  jobs: Job[] = [];
  constructor(private jobService: JobserviceService) {}

  ngOnInit(): void {
    this.jobService.listJobs().subscribe((jobs: any) => {
      this.jobs = jobs.jobs;
    });
  }

  deleteJobById(jobId: any) {
    this.jobService.deleteJobById(jobId).subscribe((response) => {
      this.jobService.listJobs().subscribe((jobs: Job[]) => {
        this.jobs = jobs;
      });
    });
  }
}
