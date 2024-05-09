import { Routes } from '@angular/router';
import { HomeComponent } from './components/pages/home/home.component';
import { ListjobsComponent } from './components/pages/listjobs/listjobs.component';
import { JobdetailsComponent } from './components/pages/jobdetails/jobdetails.component';
import { JobformComponent } from './components/pages/jobform/jobform.component';
import { LoginComponent } from './components/pages/login/login.component';
import { LayoutComponent } from './components/layout/layout.component';
import { LoginLayoutComponent } from './components/login-layout/login-layout.component';

export const routes: Routes = [
  {
    path: '',
    component: LoginLayoutComponent,
    children: [{ path: '', component: LoginComponent }],
  },
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'register_job', component: JobformComponent },
      { path: 'jobs', component: ListjobsComponent },
      { path: 'update_job/:id', component: JobformComponent },
      { path: 'job_details/:id', component: JobdetailsComponent },
    ],
  },
];
