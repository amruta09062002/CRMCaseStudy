
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'

export interface Customer {
customerId: any;
customerName: any;
customerEmail: any;
phoneNo: string;
interactions: string;
}

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  
private baseUrl = 'http://localhost:8002/customer';

  constructor(private http: HttpClient) {}

 
private getHeaders(): HttpHeaders {
  const token = localStorage.getItem('jwtToken');
  return new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${token}`
  });
}

  createCustomer(customer: Customer): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/createCustomer`, customer, {
      //headers: this.getHeaders()
      responseType: 'text' as 'json'
    });

}

  getAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.baseUrl}/getAllCustomers`, {
      headers: this.getHeaders()
    });
  }
  
getCustomerById(id: number) :Observable<string>{ 
  return this.http.get<string>(`${this.baseUrl}/getCustomerById/${id}`, {
      headers: this.getHeaders()
    });
  }

updateCustomer(id: number, customer: Customer): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/updateCustomerById/${id}`, customer, {
      responseType: 'text' as 'json'
    });
  }

 deleteCustomer(id: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/deleteCustomerById/${id}`, {
      //headers: this.getHeaders()
      responseType: 'text' as 'json'
    });
  }
}

