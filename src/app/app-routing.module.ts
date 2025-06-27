import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MarketingComponent } from './marketing/marketing.component';
import { AboutComponent } from './about/about.component';
import { SupportComponent } from './support/support.component';
import { SalesComponent } from './sales/sales.component';
import { AdminComponent } from './admin/admin.component';

const routes: Routes = [
   { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'marketing', component: MarketingComponent },
  { path: 'about', component: AboutComponent },
  { path: 'sales', component: SalesComponent },
  { path: 'support', component: SupportComponent },
  { path: 'admin', component: AdminComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
