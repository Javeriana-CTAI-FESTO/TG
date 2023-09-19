import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Estations } from '../dashboard.service';
@Injectable({
  providedIn: 'root'
})
export class ReportsServiceService {

  baseUrl = 'http://172.21.26.53:8080/api/admin/reports';
  constructor(private http: HttpClient) { }
  getReports(): Observable<ResponseData[]> {
    return this.http.get<ResponseData[]>(this.baseUrl);
  }
  getReportsFails(): Observable<ResponseData[]> {
    return this.http.get<ResponseData[]>(this.baseUrl + '/fails');
  }
  getReportById(id: number): Observable<Report[]> {
    return this.http.get<Report[]>(this.baseUrl + '/' + id);
  }
  getReportFailsById(id: number): Observable<Report[]> {
    return this.http.get<Report[]>(this.baseUrl + '/fails/' + id);
  }
}

export interface Report {
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
  resource: Estations;
  report: Report;
}