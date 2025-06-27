// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthService {
  
// private baseUrl = 'http://localhost:8001/auth';

//   constructor(private http: HttpClient) {}

//   register(user: any): Observable<any> {
//     return this.http.post(`${this.baseUrl}/register`, user, { responseType: 'text' });
//   }

//   login(credentials: any): Observable<any> {
//     return this.http.post(`${this.baseUrl}/login`, credentials, { responseType: 'text' });
//   }
// }


import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

export interface JwtPayload {
  sub: string;
  role: string;
  exp: number;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private baseUrl = 'http://localhost:8001/auth';

  constructor(private http: HttpClient) {}

  
login(credentials: any): Observable<string> {
  return this.http.post('http://localhost:8001/auth/login', credentials, {
    responseType: 'text'  // 👈 This tells Angular not to parse it as JSON
  });
}

  // register(user: any): Observable<any> {
  //   return this.http.post(`${this.baseUrl}/register`, user);
  // }

  
register(user: any): Observable<any> {
  return this.http.post(`${this.baseUrl}/register`, user, { responseType: 'text' });
}

  getUserRole(): string | null {
    const token = localStorage.getItem('token');
    if (token) {
      const decoded = jwtDecode<JwtPayload>(token);
      return decoded.role;
    }
    return null;
  }
  
}
