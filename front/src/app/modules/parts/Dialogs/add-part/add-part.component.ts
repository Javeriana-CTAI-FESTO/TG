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
    pictureName: new FormControl(''),
    pictureFolder: new FormControl(''),
    basePallet: new FormControl('', Validators.required),
    mrpType: new FormControl('', Validators.required),
    safetyStock: new FormControl('', Validators.required),
    lotSize: new FormControl('', Validators.required),
    defaultResourceId: new FormControl('', Validators.required)
  }, { validators: pictureValidator });

  //folders: string[] = ['AFB', 'Cylindrical material', 'Default', 'EMCO', 'Factory4', 'iCIM', 'MPS', 'MPS500', 'MPS-PA', 'PROLOG', 'Sonder', 'SpareParts', 'TransferFactoryLight', 'TransferFactory', 'TransferSystem'];
  folders: {name: string, image: string}[] = [
    {name: 'AFB', image: '../../../../../assets/Pictures/AFB/AFB-ASRS.png'},
    {name: 'Cylindrical material', image: '../../../../../assets/Pictures/AFB/AFB-ASRS.png'},
    {name: 'Default', image: '../../../../../assets/Pictures/AFB/AFB-ASRS.png'},
    // ... y así sucesivamente para cada carpeta
  ];
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
    const pictureNameControl = this.partForm.get('pictureName'); 
    if (pictureControl && pictureNameControl) {
      pictureControl.setValue(file ? file : '');
      pictureNameControl.setValue(file ? file.name : ''); 
    } else {
      console.error('El control "picture" o "pictureName" no existe en el formulario');
    }
  }

  onSubmit() {
    if (this.partForm.valid) {
      const formData = this.partForm.value;
      const pieza: Pieza = {
        partNumber: formData.partNumber,
        description: formData.description,
        type: formData.type,
        workPlanNumber: formData.workPlanNumber,
        picture: formData.pictureName,
        basePallet: formData.basePallet,
        mrpType: formData.mrpType,
        safetyStock: formData.safetyStock,
        lotSize: formData.lotSize,
        defaultResourceId: formData.defaultResourceId
      };
      const file: File = this.partForm.controls['picture'].value;
      const dropdownValue: {name: string, image: string} = this.partForm.controls['pictureFolder'].value;
  
      if (file) {
        const reader = new FileReader();
  
        reader.onload = () => {
          console.log(pieza);
          console.log('Imagen en base64:', reader.result); // Nuevo console.log para la imagen en base64
  
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
      } else if (dropdownValue && dropdownValue.name !== '') {
        pieza.picture = dropdownValue.name;
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