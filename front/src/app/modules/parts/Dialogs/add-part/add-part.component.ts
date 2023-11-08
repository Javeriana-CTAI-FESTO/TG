import { Component, Inject, OnInit, ViewChild, ElementRef } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Output, EventEmitter } from '@angular/core';
import { WorkplanServiceService, Workplan } from 'src/app/modules/posts/workplan-service.service';
import { PiezasServiceService, Pieza } from '../../piezas-service.service';
import { ToastrService } from 'ngx-toastr';
import { AbstractControl } from '@angular/forms';

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

  workPlans: Workplan[] = [];
  isLoading = false;

  constructor(
    public dialogRef: MatDialogRef<AddPartComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,  // Aquí es donde inyectas los datos
    private workplanService: WorkplanServiceService,
    private piezasService: PiezasServiceService,
    private toastr: ToastrService
  ) { }
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
      if (file) {
        const reader = new FileReader();
        reader.onload = () => {
          // Cuando el archivo se ha leído, crea un Blob a partir del resultado
          const blob = new Blob([new Uint8Array(reader.result as ArrayBuffer)]);
          // Almacena el Blob en lugar del archivo en el control del formulario
          pictureControl.setValue(blob);
        };
        reader.readAsArrayBuffer(file);
        pictureNameControl.setValue(file.name);
      } else {
        pictureControl.setValue('');
        pictureNameControl.setValue('');
      }
    } else {
      console.error('El control "picture" o "pictureName" no existe en el formulario');
    }
  }
  onSubmit() {
    this.isLoading = true;
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
      const file: Blob = this.partForm.controls['picture'].value;
      const fileName: string = this.partForm.controls['pictureName'].value;
      const dropdownValue: { name: string, image: string } = this.partForm.controls['pictureFolder'].value;

      if (file) {
        this.piezasService.uploadImage(file, fileName).subscribe(response => {
          console.log('Imagen subida con éxito:', response);
          this.toastr.success(response, 'Successfully');

          this.piezasService.agregarPieza(pieza).subscribe(response => {
            this.dialogRef.close();
            this.piezaAdded.emit();
            this.toastr.success('Part added successfully', 'Success');
          }, error => {
            console.error('Error al agregar la pieza', error);
            this.toastr.error('Error adding part', 'Error');
          });

        }, error => {
          console.error('Error al subir la imagen', error);
          this.toastr.error(error.error, 'Error');
        });

      } else if (dropdownValue && dropdownValue.name !== '') {
        pieza.picture = dropdownValue.name;

        this.piezasService.agregarPieza(pieza).subscribe(response => {
          this.dialogRef.close();
          this.piezaAdded.emit();
          this.toastr.success('Part added successfully', 'Success');
        }, error => {
          console.error('Error al agregar la pieza', error);
          this.toastr.error('Error adding part', 'Error');
        });
        
      }
    } else {
      console.error('El formulario no es válido');
    }
  }

  clearPicture() {
    this.partForm.controls['picture'].setValue(null);
    this.pictureInput.nativeElement.value = '';
  }
  processFolderName(folderName: string): string {
    let extension = folderName.slice(folderName.lastIndexOf('.'));
    let nameWithoutExtension = folderName.slice(0, folderName.lastIndexOf('.'));
    let parts = nameWithoutExtension.split('_');
    if (parts.length > 1) {
      let lastPart = parts.pop();
      parts.unshift(lastPart + '/');
    }
    let processedName = parts.join('_') + extension;
    return processedName.replace('/_', '/');
  }
}