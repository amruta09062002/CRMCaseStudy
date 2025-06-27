import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Campaign {
  title: string;
  description: string;
  targetSegment: string;
}
 
@Injectable({
  providedIn: 'root'
})
export class MarketingService {
  private baseUrl = 'http://localhost:8003/marketing';
 
  constructor(private http: HttpClient) {}
 
  createCampaign(campaign: Campaign): Observable<string> {
    return this.http.post(`${this.baseUrl}/createCampaign`, campaign, { responseType: 'text' });
  }
 
  getAllCampaigns(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(`${this.baseUrl}/getAllCampaigns`);
  }
}
 