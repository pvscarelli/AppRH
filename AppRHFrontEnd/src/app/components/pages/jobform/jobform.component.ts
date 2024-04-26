import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { JobserviceService } from '../../../services/jobservice.service';
import { ActivatedRoute } from '@angular/router';
import { JobDto } from '../../../dtos/jobdto';
import { Job } from '../../../models/job';

@Component({
  selector: 'app-form-job',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './jobform.component.html',
  styleUrl: './jobform.component.scss',
})
export class JobformComponent implements OnInit {
  message = '';
  mode = 'register';
  jobId = '';
  jobDto = new JobDto();
  jobSalary = '';

  constructor(
    private jobService: JobserviceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.toggleMode();
  }

  toggleMode() {
    this.route.url.subscribe((url) => {
      if (url[0]?.path === 'update_job') {
        this.mode = 'update';
        this.route.params.subscribe((params) => {
          this.jobId = params['id'];
          this.jobService.getJob(this.jobId).subscribe((job: Job) => {
            this.jobDto.name = job.name;
            this.jobDto.expiration = job.expiration;
            this.jobSalary = job.salary.toString();
            this.jobDto.description = job.description;
          });
        });
      }
    });
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.jobDto.salary = Number(this.jobSalary);
      if (this.mode === 'register') {
        this.registerMode(form);
      } else {
        this.updateMode(form);
      }
    } else {
      this.message = 'Verifique os campos...';
    }
  }

  registerMode(form: NgForm) {
    this.jobService.createJob(this.jobDto).subscribe(
      (response) => {
        this.message = 'Vaga cadastrada com sucesso!';
        form.resetForm();
      },
      (error) => {
        this.message = 'Verifique os campos...';
      }
    );
  }

  updateMode(form: NgForm) {
    this.jobService.updateJob(this.jobId, this.jobDto).subscribe(
      (response) => {
        this.message = 'Vaga atualizada com sucesso!';
        form.resetForm();
        this.mode = 'register';
      },
      (error) => {
        this.message = 'Verifique os campos...';
      }
    );
  }
}
