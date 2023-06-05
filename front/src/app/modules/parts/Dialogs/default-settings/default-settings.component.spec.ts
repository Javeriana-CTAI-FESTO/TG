import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultSettingsComponent } from './default-settings.component';

describe('DefaultSettingsComponent', () => {
  let component: DefaultSettingsComponent;
  let fixture: ComponentFixture<DefaultSettingsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DefaultSettingsComponent]
    });
    fixture = TestBed.createComponent(DefaultSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
