import { Component } from '@angular/core';
import { Customer, CustomerService } from '../customer.service';

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent {

customers: Customer[] = [];
  selectedCustomerId: number = 0;
fetchedCustomer: Customer | null = null;
  message: string | null = null;
  msg1: string | null = null;
  selectedAction: string = ' ';

  constructor(private customerService: CustomerService) {}

  showAllCustomers() {
    this.customerService.getAllCustomers().subscribe(data => {
      this.customers = data;
    });
  }

getCustomerById() {
  this.customerService.getCustomerById(this.selectedCustomerId).subscribe(
    (data: any) => {
      if (data && data.customerId) {
        this.fetchedCustomer = data;
        this.message = null;
      } else {
        this.fetchedCustomer = null;
        this.message = 'Customer ID not present';
      }
    },
    error => {
      this.fetchedCustomer = null;
      this.message = 'Customer ID not present';
      console.error('Error fetching customer:', error);
    }
  );
}
}

