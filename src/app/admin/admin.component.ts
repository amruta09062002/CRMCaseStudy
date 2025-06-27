import { Component } from '@angular/core';
import { CustomerService, Customer } from '../customer.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

newCustomer: Customer = {
  customerId: null,
  customerName: '',
  customerEmail: '',
  phoneNo: '',
  interactions: ''
  
};
selectedAction: string = ' ';

constructor(private customerService: CustomerService) {}

deleteCustomerId: number = 0;
customers: Customer[] = [];
selectedCustomerId: number = 0;
updateCustomerId: number = 0;
fetchedCustomer: Customer | null = null;
customerToUpdate: any;
message: string | null = null;
msg1: string | null = null;
msg2: string | null = null;
msg3: string | null = null;

createCustomer() {
  this.customerService.createCustomer(this.newCustomer).subscribe({
    next: (res) => {
      console.log('Customer created successfully:', res);
      this.msg2 = 'Customer created successfully.';
      this.newCustomer = {
        customerId: null,
        customerName: '',
        customerEmail: '',
        phoneNo: '',
        interactions: ''
      };
    },
    error: (err) => {
      console.error('Error occurred while creating customer:', err);
      this.msg2 = 'Failed to create customer.';
    },
    complete: () => {
      if (!this.msg2) {
        this.msg2 = 'Customer updated successfully.';
      }
    }
  });
}



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

deleteCustomer() {
  this.customerService.deleteCustomer(this.deleteCustomerId).subscribe({
    next: (res) => {
      this.msg3 = 'Customer deleted successfully.';
    },
    error: (err) => {
      console.error('Delete error:', err);
      this.msg3 = 'Failed to delete customer.';
    }
  });
}


}