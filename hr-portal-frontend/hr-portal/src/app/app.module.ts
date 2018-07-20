import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Injectable } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './components/app.component';
import { HttpClientModule, HttpInterceptor, HttpRequest, HttpHandler, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppService } from './services/app.service';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { UiModule } from './components/ui/ui.module';
import { DepartmentsComponent } from './components/hr/departments/departments.component';
import { MatTableModule } from '@angular/material/table';
import { DisplayEmployeeComponent } from './components/hr/display-employee/display-employee.component';

import { MatInputModule}  from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { CreateDepartmentComponent } from './components/hr/dialogs/create-department/create-department.component';
import { EditEmployeeComponent } from './components/hr/dialogs/edit-employee/edit-employee.component';
import {MatIconModule} from '@angular/material/icon';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ChangeManagerComponent } from './components/hr/dialogs/change-manager/change-manager.component';





/*
  Getting a 401 response back from Spring Security prompots the browser
  to pop-up a username & password dialog. The below code to intercept all
  xhr requests and and add a header to suppress this dialog.

  make sure that cookies are included with each request
*/
@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      withCredentials: true,
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'departments', component: DepartmentsComponent},
  { path: 'employee', component: DisplayEmployeeComponent},
  { path: '404', component: PageNotFoundComponent},
  { path: '**', redirectTo: '/404'}
];


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    DepartmentsComponent,
    DisplayEmployeeComponent,
    CreateDepartmentComponent,
    EditEmployeeComponent,
    PageNotFoundComponent,
    ChangeManagerComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    UiModule,
    MatTableModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatListModule,
    MatSnackBarModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatIconModule
  ],
  providers: [AppService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [AppComponent],
  entryComponents: [
    CreateDepartmentComponent,
    EditEmployeeComponent,
    ChangeManagerComponent
  ]
})

export class AppModule { }
