import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';
import { Estations } from '../dashboard.service';
import { environment } from 'src/enviroments/enviroment';
@Injectable({
  providedIn: 'root'
})
export class ReportsServiceService {
  constructor(private http: HttpClient) { }

  reports: ResponseData[] =[];
  getReports(): Observable<ResponseData[]> {
    if (!this.reports.length) {
      return this.http.get<ResponseData[]>(environment.urlBase + 'admin/reports').pipe(tap(data => {
        this.reports = data;
      }));
    } else {
      return of(this.reports);
    }
  }
  failsReports: ResponseData[] = [];
  getReportsFails(): Observable<ResponseData[]> {
    if (!this.failsReports.length) {
      return this.http.get<ResponseData[]>(environment.urlBase + 'admin/reports/fails').pipe(tap(data => {
        this.failsReports = data;
      }));
    } else {
      return of(this.failsReports);
    }
  }
  getReportById(id: number): Observable<Report[]> {
    return this.http.get<Report[]>(environment.urlBase + '/' + id);
  }
  getReportFailsById(id: number): Observable<Report[]> {
    return this.http.get<Report[]>(environment.urlBase + 'admin/reports/fails/' + id);
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