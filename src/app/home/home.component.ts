// import { Component, OnInit } from '@angular/core';
// import { AuthService } from '../auth.service';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-home',
//   templateUrl: './home.component.html',
//   styleUrls: ['./home.component.css']
// })
// export class HomeComponent {
 
// credentials = {
//     username: '',
//     password: ''
//   };
 
//   constructor(private authService: AuthService, private router: Router) {}
 
//   onLogin() {
//     if(!this.credentials.username || !this.credentials.password)
//     {
//       alert('All fields are required');
//       return;
//     }
//   this.authService.login(this.credentials).subscribe({
//     next: token => {
//       localStorage.setItem('token',token);
//       this.router.navigate(['/dashboard']);
//     },
//     error: err => alert('Login failed: '+err.error)
//   })
// }
// }
 

import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  credentials = {
    username: '',
    password: ''
  };

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    if (!this.credentials.username || !this.credentials.password) {
      alert('All fields are required');
      return;
    }

    this.authService.login(this.credentials).subscribe({
      next: token => {
        localStorage.setItem('token', token);
        const role = this.authService.getUserRole();

        if (role === 'ADMIN') {
          this.router.navigate(['/admin']);
        } else if (role === 'SALES') {
          this.router.navigate(['/sales']);
        } else if (role === 'SUPPORT') {
          this.router.navigate(['/support']);
        } else {
          alert('Unknown role');
        }
      },
      error: err => {
        console.error('Login error:', err);
        alert('Login failed: ' + (err.error?.message || err.message || 'Unknown error'));
      }
    });
  }
}
