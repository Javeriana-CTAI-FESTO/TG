import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourceInfoDialogComponent } from './resource-info-dialog.component';

describe('ResourceInfoDialogComponent', () => {
  let component: ResourceInfoDialogComponent;
  let fixture: ComponentFixture<ResourceInfoDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResourceInfoDialogComponent]
    });
    fixture = TestBed.createComponent(ResourceInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
