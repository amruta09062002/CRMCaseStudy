import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})


export class AboutComponent {

  crmFeatures = [
    {
      title: 'Customer Data Management',
      description: 'Store and manage customer information efficiently in a centralized database.'
    },
    {
      title: 'Sales Automation',
      description: 'Automate sales processes to improve productivity and close deals faster.'
    },
    {
      title: 'Marketing Campaigns',
      description: 'Plan, execute, and track marketing campaigns to reach your target audience.'
    },

{
      title: 'Customer Support',
      description: 'Provide timely support and improve customer satisfaction with integrated tools.'
    },
    {
      title: 'Analytics & Reporting',
      description: 'Gain insights into customer behavior and business performance with detailed reports.'
    }
  ];
}

