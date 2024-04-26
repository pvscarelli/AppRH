import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Job } from '../models/job';
import { JobDto } from '../dtos/jobdto';

@Injectable({
  providedIn: 'root',
})
export class JobserviceService {
  apiUrl = 'http://localhost:8080/v1/jobs';
  constructor(private http: HttpClient) {}

  listJobs(): Observable<Job[]> {
    return this.http
      .get<Job[]>(this.apiUrl)
      .pipe(map((response) => response as []));
  }

  getJob(jobId: any): Observable<Job> {
    return this.http
      .get(`${this.apiUrl}/${jobId}`)
      .pipe(map((response) => response as Job));
  }

  createJob(createJobDto: JobDto) {
    return this.http.post(`${this.apiUrl}/post`, createJobDto);
  }

  updateJob(jobId: any, updateJobDto: JobDto) {
    return this.http.put(`${this.apiUrl}/update/${jobId}`, updateJobDto);
  }

  deleteJobById(jobId: any) {
    return this.http.delete(`${this.apiUrl}/delete/${jobId}`);
  }
}
