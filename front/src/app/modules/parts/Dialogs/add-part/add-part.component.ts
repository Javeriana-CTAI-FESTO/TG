import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Output, EventEmitter } from '@angular/core';
import { WorkplanServiceService, Workplan } from 'src/app/modules/posts/workplan-service.service';
import { PiezasServiceService, Pieza } from '../../piezas-service.service';
import { ToastrService } from 'ngx-toastr';
import { AbstractControl} from '@angular/forms';

export const pictureValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const picture = (control as FormGroup).get('picture');
  const pictureFolder = (control as FormGroup).get('pictureFolder');

  return picture?.value || pictureFolder?.value ? null : { 'pictureRequired': true };
};

@Component({
  selector: 'app-add-part',
  templateUrl: './add-part.component.html',
  styleUrls: ['./add-part.component.css']
})
export class AddPartComponent implements OnInit {
  @Output() piezaAdded = new EventEmitter<void>();
  @ViewChild('pictureInput') pictureInput!: ElementRef;
  partForm: FormGroup = new FormGroup({
    partNumber: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    type: new FormControl(3, Validators.required),
    workPlanNumber: new FormControl('', Validators.required),
    picture: new FormControl(''),
    pictureFolder: new FormControl(''),
    basePallet: new FormControl('', Validators.required),
    mrpType: new FormControl('', Validators.required),
    safetyStock: new FormControl('', Validators.required),
    lotSize: new FormControl('', Validators.required),
    defaultResourceId: new FormControl('', Validators.required)
  }, { validators: pictureValidator });

  folders: string[] = ['AFB', 'Cylindrical material', 'Default', 'EMCO', 'Factory4', 'iCIM', 'MPS', 'MPS500', 'MPS-PA', 'PROLOG', 'Sonder', 'SpareParts', 'TransferFactoryLight', 'TransferFactory', 'TransferSystem'];
  workPlans: Workplan[] = [];

  constructor(public dialogRef: MatDialogRef<AddPartComponent>, private workplanService: WorkplanServiceService, private piezasService: PiezasServiceService, private toastr: ToastrService) { }

  ngOnInit() {
    this.workplanService.getWorkPlansPorDefecto().subscribe(workPlans => {
      this.workPlans = workPlans;
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    const pictureControl = this.partForm.get('picture');
    if (pictureControl) {
      pictureControl.setValue(file ? file : '');
    } else {
      console.error('El control "picture" no existe en el formulario');
    }
  }

  onSubmit() {
    if (this.partForm.valid) {
      const pieza: Pieza = this.partForm.value;
      const file: File = this.partForm.controls['picture'].value;
      const dropdownValue: string = this.partForm.controls['pictureFolder'].value;
  
      if (file) {
        const reader = new FileReader();
  
        reader.onload = () => {
          pieza.picture = reader.result as string;
          console.log(pieza);
  
          /* this.piezasService.agregarPieza(pieza).subscribe(response => {
            this.dialogRef.close();
            this.piezaAdded.emit();
            this.toastr.success('Part added successfully', 'Success');
          }, error => {
            console.error('Error al agregar la pieza', error);
            this.toastr.error('Error adding part', 'Error');
          });*/
        };
  
        reader.readAsDataURL(file);
      } else if (dropdownValue && dropdownValue !== '') {
        pieza.picture = dropdownValue;
        console.log(pieza);
  
        /* this.piezasService.agregarPieza(pieza).subscribe(response => {
          this.dialogRef.close();
          this.piezaAdded.emit();
          this.toastr.success('Part added successfully', 'Success');
        }, error => {
          console.error('Error al agregar la pieza', error);
          this.toastr.error('Error adding part', 'Error');
        });*/
      }
    } else {
      console.error('El formulario no es válido');
    }
  }
  
  clearPicture() {
    this.partForm.controls['picture'].setValue(null);
    this.pictureInput.nativeElement.value = '';
  }
}