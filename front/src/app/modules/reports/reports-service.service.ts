import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ReportsServiceService {

  baseUrl = 'http://localhost:8080/api/admin/reports';
  constructor(private http: HttpClient) { }
  getReports(): Observable<ResponseData[]> {
    return this.http.get<ResponseData[]>(this.baseUrl);
  }
  getReportsFails(): Observable<ResponseData[]> {
    return this.http.get<ResponseData[]>(this.baseUrl + '/fails');
  }
}
export interface Resource {
  id: number;
  name: string;
  description: string;
  plcType: number;
  ip: string;
  picture: string;
  parallelProcessing: boolean;
  automatic: boolean;
  webPage: string;
  openWithDefaultBrowser: boolean;
  topologyType: number;
  agv: boolean;
  resourceType: number;
}

interface Report {
  timestamp: string;
  id: number;
  automaticMode: boolean;
  manualMode: boolean;
  busy: boolean;
  reset: boolean;
  errorL0: boolean;
  errorL1: boolean;
  errorL2: boolean;
}

export interface ResponseData {
  resource: Resource;
  report: Report;
}