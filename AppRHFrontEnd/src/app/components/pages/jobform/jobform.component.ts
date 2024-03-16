import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { JobserviceService } from '../../../services/jobservice.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-form-job',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './jobform.component.html',
  styleUrl: './jobform.component.scss',
})
export class JobformComponent implements OnInit {
  successMessage = '';
  errorMessage = '';
  mode = 'register';
  jobName = '';
  jobDate = '';
  jobSalary = null;
  jobDescription = '';

  constructor(
    private jobService: JobserviceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.toggleMode();
  }

  toggleMode() {
    this.route.url.subscribe((url) => {
      if (url[0]?.path === 'updateJob') {
        this.mode = 'update';
        this.route.params.subscribe((params) => {
          const jobId = params['id'];
          this.jobService.getJob(jobId).subscribe((job: any) => {
            this.jobName = job.jobName;
            this.jobDate = job.jobDate;
            this.jobSalary = job.jobSalary;
            this.jobDescription = job.jobDescription;
          });
        });
      }
    });
  }

  onSubmit(form: NgForm) {
    const formData = new FormData(document.querySelector('form')!);
    if (this.mode === 'register') {
      this.jobService.createJob(formData).subscribe(
        (response) => {
          this.errorMessage = '';
          this.successMessage = 'Vaga cadastrada com sucesso!';
          form.resetForm();
        },
        (error) => {
          this.successMessage = '';
          this.errorMessage = 'Verifique os campos...';
        }
      );
    } else {
      let jobId = '';
      this.route.params.subscribe((params) => {
        jobId = params['id'];
      });
      this.jobService.updateJob(jobId, formData).subscribe(
        (response) => {
          this.errorMessage = '';
          this.successMessage = 'Vaga alterada com sucesso!';
          form.resetForm();
          this.mode = 'register';
        },
        (error) => {
          this.successMessage = '';
          this.errorMessage = 'Verifique os campos...';
        }
      );
    }
  }
}
