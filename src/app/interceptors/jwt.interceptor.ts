import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent
} from '@angular/common/http';
import { Observable } from 'rxjs';
 
@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(){
    console.log("starting interceptor");
  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const user = (localStorage.getItem('token'));
    let token = user;
 
    if (token) {
      token = token.replace(/\s/g, ''); // Remove all whitespace (spaces, newlines, etc.)
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      }
    );
    console.log("checking");
    }
 
    return next.handle(req);
  }
}
 