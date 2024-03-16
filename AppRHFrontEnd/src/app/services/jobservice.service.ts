import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class JobserviceService {
  apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) {}

  listJobs() {
    return this.http
      .get(`${this.apiUrl}/jobs`)
      .pipe(map((response) => response as []));
  }

  getJob(jobId: any) {
    return this.http.get(`${this.apiUrl}/jobs/${jobId}`);
  }

  createJob(formData: FormData) {
    return this.http.post(`${this.apiUrl}/postJob`, formData);
  }

  updateJob(jobId: any, formData: any) {
    return this.http.put(`${this.apiUrl}/editJob/${jobId}`, formData);
  }

  deleteJobById(jobId: any) {
    return this.http.delete(`${this.apiUrl}/deleteJob/${jobId}`);
  }
}
