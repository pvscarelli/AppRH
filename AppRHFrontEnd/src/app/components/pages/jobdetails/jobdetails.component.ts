import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { JobserviceService } from '../../../services/jobservice.service';
import { ActivatedRoute } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { ApplicantService } from '../../../services/applicant.service';
import { ApplicantDto } from '../../../dtos/applicantdto';
import { Job } from '../../../models/job';
import { Applicant } from '../../../models/applicant';

@Component({
  selector: 'app-jobdetails',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './jobdetails.component.html',
  styleUrl: './jobdetails.component.scss',
})
export class JobdetailsComponent implements OnInit {
  jobName = '';
  jobDate = '';
  jobSalary = '';
  jobDescription = '';
  applicants: Applicant[] = [];
  jobId: any = '';
  message = '';
  applicantDto: ApplicantDto = new ApplicantDto();

  constructor(
    private jobService: JobserviceService,
    private route: ActivatedRoute,
    private applicanteService: ApplicantService
  ) {}

  ngOnInit(): void {
    this.route.url.subscribe((url) => {
      this.jobId = url[1].path;
      this.jobService.getJob(url[1].path).subscribe((job: Job) => {
        this.jobName = job.name;
        this.jobDate = job.expiration.toString();
        this.jobSalary = job.salary.toString();
        this.jobDescription = job.description;
        this.applicants = job.applicants;
      });
    });
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.applicanteService
        .addApplicant(this.applicantDto, this.jobId)
        .subscribe(
          (response) => {
            this.message = 'Candidato cadastrado com sucesso!';
            form.resetForm();
            this.jobService.getJob(this.jobId).subscribe((job: Job) => {
              this.applicants = job.applicants;
            });
          },
          (error) => {
            this.message = error.error.message;
          }
        );
    } else {
      console.log(form);
      this.message = 'Por favor, confira todos os campos.';
    }
  }

  deleteApplicant(cpf: string, jobId: any) {
    this.applicanteService.removeApplicant(cpf, jobId).subscribe((response) => {
      const shownApplicant = document.getElementById(`applicant-${cpf}`)!;
      shownApplicant.remove();
    });
  }

  onCpfChange(cpf: string) {
    if (cpf !== null) {
      let inputLength = cpf.length;
      this.cpfMask(inputLength);
    }
  }

  cpfMask(inputLength: any) {
    if (inputLength === 3 || inputLength === 7) {
      this.applicantDto.cpf += '.';
    } else if (inputLength === 11) {
      this.applicantDto.cpf += '-';
    }
  }

  onPaste(event: ClipboardEvent) {
    event.preventDefault();
  }
}
