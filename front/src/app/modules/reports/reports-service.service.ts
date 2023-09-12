import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ReportsServiceService {

  constructor(private http: HttpClient) { }

  getFails() {
    this.http.get('http://localhost:8080/api/admin/reports/fails').subscribe(response => {
      console.log(response);
    });
  }
}
