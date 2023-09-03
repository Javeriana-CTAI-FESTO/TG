import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultOtherSettingsComponent } from './default-other-settings.component';

describe('DefaultOtherSettingsComponent', () => {
  let component: DefaultOtherSettingsComponent;
  let fixture: ComponentFixture<DefaultOtherSettingsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DefaultOtherSettingsComponent]
    });
    fixture = TestBed.createComponent(DefaultOtherSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
