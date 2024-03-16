import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { JobserviceService } from '../../../services/jobservice.service';
import { ActivatedRoute } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { ApplicantService } from '../../../services/applicant.service';

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
  applicants: any = [];
  jobId: any = '';
  errorWarningMessage = '';
  successMessage = '';
  //variaveis para referenciar no html e limpar o form
  applicantName = '';
  cpfApplicant = '';
  applicantMail = '';

  constructor(
    private jobService: JobserviceService,
    private route: ActivatedRoute,
    private applicanteService: ApplicantService
  ) {}
  ngOnInit(): void {
    this.route.url.subscribe((url) => {
      this.jobId = url[1].path;
      this.jobService.getJob(url[1].path).subscribe((job: any) => {
        this.jobName = job.jobName;
        this.jobDate = job.jobDate;
        this.jobSalary = job.jobSalary;
        this.jobDescription = job.jobDescription;
        this.applicants = job.applicants;
      });
    });
  }
  onSubmit(form: NgForm) {
    const dataForm = new FormData(document.querySelector('form')!);
    this.route.url.subscribe((url) => {
      this.applicanteService.addApplicant(dataForm, url[1]).subscribe(
        (response) => {
          this.errorWarningMessage = '';
          this.successMessage = 'Candidato cadastrado com sucesso!';
          form.resetForm();
          this.jobService.getJob(url[1].path).subscribe((job: any) => {
            this.applicants = job.applicants;
          });
        },
        (error) => {
          this.errorWarningMessage =
            error.error.message || 'Verifique os campos';
          this.successMessage = '';
        }
      );
    });
  }
  deleteApplicant(cpf: string, jobId: any) {
    this.applicanteService.removeApplicant(cpf, jobId).subscribe((response) => {
      const element = document.getElementById(`applicant-${cpf}`);
      element?.remove();
    });
  }
}
