import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicantDto } from '../dtos/applicantdto';

@Injectable({
  providedIn: 'root',
})
export class ApplicantService {
  apiUrl = 'http://localhost:8080/v1';

  constructor(private http: HttpClient) {}

  addApplicant(createApplicantDto: ApplicantDto, jobId: any) {
    return this.http.post(
      `${this.apiUrl}/jobs/add_applicant/${jobId}`,
      createApplicantDto
    );
  }

  removeApplicant(cpf: string, jobId: any) {
    return this.http.delete(
      `${this.apiUrl}/jobs/delete_applicant/${jobId}/${cpf}`
    );
  }
}
