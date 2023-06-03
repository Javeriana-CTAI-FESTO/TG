import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonaDialogComponent } from './persona-dialog.component';

describe('PersonaDialogComponent', () => {
  let component: PersonaDialogComponent;
  let fixture: ComponentFixture<PersonaDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PersonaDialogComponent]
    });
    fixture = TestBed.createComponent(PersonaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
