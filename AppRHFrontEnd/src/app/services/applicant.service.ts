import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ApplicantService {
  apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) {}

  addApplicant(formData: FormData, jobId: any) {
    return this.http.post(`${this.apiUrl}/addApplicant/${jobId}`, formData);
  }

  removeApplicant(cpf: string, jobId: any) {
    return this.http.delete(`${this.apiUrl}/deleteApplicant/${cpf}/${jobId}`);
  }
}
