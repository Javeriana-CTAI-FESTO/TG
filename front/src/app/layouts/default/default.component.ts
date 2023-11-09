import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit {

  sideBarOpen = true;
  isSmallScreen = false;

  constructor(private breakpointObserver: BreakpointObserver) { }

  ngOnInit(): void {
    this.breakpointObserver.observe([
      Breakpoints.Handset
    ]).subscribe(result => {
      this.isSmallScreen = result.matches;
    });
  }

  sideBarToggler(event: any) {
    this.sideBarOpen = !this.sideBarOpen;
  }

  onRouteSelected() {
    if (this.isSmallScreen) {
      this.sideBarOpen = false;
    }
  }
}