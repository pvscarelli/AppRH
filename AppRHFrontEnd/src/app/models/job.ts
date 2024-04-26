import { Applicant } from './applicant';

export interface Job {
  id: number;
  name: string;
  description: string;
  salary: number;
  expiration: Date;
  applicants: Applicant[];
}
