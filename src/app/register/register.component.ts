import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
 
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
 
user = {
    username: '',
    password: '',
    role: ''
  };
 
  constructor(private authService: AuthService, private router: Router) {}
 
// onRegister() {
//     if(!this.user.username || !this.user.password || !this.user.role)
//     {
//       alert("All fields are required");
//       return;
//     }
//     this.authService.register(this.user).subscribe({
//       next: () => {
//         alert('Registration successful!');
//         this.router.navigate(['/login']);
//       },
//       error: err => alert('Registration failed: ' + err.error)
//     });
//   }
// }
 
onRegister() {
    if (!this.user.username || !this.user.password || !this.user.role) {
      alert("All fields are required");
      return;
}

this.authService.register(this.user).subscribe({
      next: (response) => {
        console.log('Registration response:', response);
        alert('Registration successful!');
        this.router.navigate(['/home']);
      },


error: (err) => {
        console.error('Registration error:', err);
        const errorMessage = err.error || err.message || 'Unknown error';
        alert('Registration failed: ' + errorMessage);
      }
    });
  }


}
