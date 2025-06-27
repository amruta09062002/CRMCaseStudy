import { Component, OnInit } from '@angular/core';
import { Campaign, MarketingService } from '../marketing.service';

@Component({
  selector: 'app-marketing',
  templateUrl: './marketing.component.html',
  styleUrls: ['./marketing.component.css']
})
export class MarketingComponent implements OnInit {
  campaigns: Campaign[] = [];
  newCampaign: Campaign = {
    title: '',
    description: '',
    targetSegment: ''
  };
  selectedAction: string = '';

 
  constructor(private marketingService: MarketingService) {}
 
  ngOnInit(): void {
    //this.loadCampaigns();
  }
 
  loadCampaigns(): void {
    this.marketingService.getAllCampaigns().subscribe(data => {
      this.campaigns = data;
    });
  }
 
  createCampaign(): void {
   
    if (!this.newCampaign.title || !this.newCampaign.description || !this.newCampaign.targetSegment) {
      alert('All fields are required!');
      return;
    }
 
    this.marketingService.createCampaign(this.newCampaign).subscribe(response => {
      alert(response);
      this.newCampaign = { title: '', description: '', targetSegment: '' };
    });
  }
}