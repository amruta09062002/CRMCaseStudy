// import { Component } from '@angular/core';
// import { Customer, CustomerService } from '../customer.service';

// @Component({
//   selector: 'app-sales',
//   templateUrl: './sales.component.html',
//   styleUrls: ['./sales.component.css']
// })
// export class SalesComponent {
  
// customers: Customer[] = [];
//   selectedCustomerId: number = 0;
//   customer: Customer = {
//     customerId: null,
//     customerName: '',
//     customerEmail: '',
//     phoneNo: '',
//     interactions: ''
//   };
//   message: string = '';

//   constructor(private customerService: CustomerService) {}

// showAllCustomers() {
//     this.customerService.getAllCustomers().subscribe(data => {
//       this.customers = data;
//     });
//   }

//   getCustomerById() {
//     this.customerService.getCustomerById(this.selectedCustomerId).subscribe(data => {
//       this.message = data;
//     });
//   }

//   updateCustomer() {
//     this.customerService.updateCustomer(this.selectedCustomerId, this.customer).subscribe(response => {

// this.message = response;
//       this.showAllCustomers();
//     });
//   }

// }

import { Component } from '@angular/core';
import { CustomerService, Customer } from '../customer.service';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent {
  customers: Customer[] = [];
  selectedCustomerId: number = 0;
  updateCustomerId: number = 0;
  fetchedCustomer: Customer | null = null;
  customerToUpdate: any;
  message: string | null = null;
  msg1: string | null = null;
  selectedAction: string = '';


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

loadCustomerForUpdate() {
    this.customerService.getCustomerById(this.updateCustomerId).subscribe(data => {
      this.customerToUpdate = data; // No JSON.parse needed
    }, error => {
      console.error('Error loading customer:', error);
      this.msg1 = 'Failed to load customer.';
    });
  }
 
updateCustomer() {
  if (this.customerToUpdate) {
    this.customerService.updateCustomer(this.updateCustomerId, this.customerToUpdate).subscribe({
      next: (response) => {
        console.log('Update response:', response);
        this.msg1 = 'Customer updated successfully.';
      },
      error: (error) => {
        console.error('Update error:', error);
        this.msg1 = 'Failed to update customer.';
      },
      complete: () => {
        if (!this.msg1) {
          this.msg1 = 'Customer updated successfully.';
        }
      }
    });
   }
 }
}
