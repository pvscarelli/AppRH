import { Routes } from '@angular/router';
import { HomeComponent } from './components/pages/home/home.component';
import { ListjobsComponent } from './components/pages/listjobs/listjobs.component';
import { JobdetailsComponent } from './components/pages/jobdetails/jobdetails.component';
import { JobformComponent } from './components/pages/jobform/jobform.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'registerJob', component: JobformComponent },
  { path: 'jobs', component: ListjobsComponent },
  { path: 'updateJob/:id', component: JobformComponent },
  { path: 'jobDetails/:id', component: JobdetailsComponent },
];
