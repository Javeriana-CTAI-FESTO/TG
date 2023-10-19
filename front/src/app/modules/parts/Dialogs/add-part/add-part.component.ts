import { Component, OnInit } from '@angular/core';
import { MatDialogRef} from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Output, EventEmitter } from '@angular/core';
import { WorkplanServiceService,Workplan } from 'src/app/modules/posts/workplan-service.service';
import { PiezasServiceService, Pieza } from '../../piezas-service.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-part',
  templateUrl: './add-part.component.html',
  styleUrls: ['./add-part.component.css']
})
export class AddPartComponent implements OnInit {
  @Output() piezaAdded = new EventEmitter<void>();

  partForm: FormGroup = new FormGroup({
    partNumber: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    type: new FormControl(3, Validators.required),
    workPlanNumber: new FormControl('', Validators.required),
    picture: new FormControl('', Validators.required),
    pictureFolder: new FormControl('', Validators.required),
    basePallet: new FormControl('', Validators.required),
    mrpType: new FormControl('', Validators.required),
    safetyStock: new FormControl('', Validators.required),
    lotSize: new FormControl('', Validators.required),
    defaultResourceId: new FormControl('', Validators.required)
  });

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
      pictureControl.setValue(file ? file.name : '');
    } else {
      console.error('El control "picture" no existe en el formulario');
    }
  }

  onSubmit() {
    const pictureFolderControl = this.partForm.get('pictureFolder');
    const pictureControl = this.partForm.get('picture');
    if (pictureFolderControl && pictureControl) {
      const combinedPicture = `../../../assets/Pictures/${pictureFolderControl.value}/${pictureControl.value}`;
      const pictureFormControl = this.partForm.get('picture');
      if (pictureFormControl) {
        pictureFormControl.setValue(combinedPicture);
      } else {
        console.error('El control "picture" no existe en el formulario');
      }
    } else {
      console.error('Uno o más controles no existen en el formulario');
    }

    const pieza: Pieza = this.partForm.value;

    this.piezasService.agregarPieza(pieza).subscribe(response => {
      this.dialogRef.close();
      this.piezaAdded.emit();
      this.toastr.success('Part added successfully', 'Success');
    }, error => {
      console.error('Error al agregar la pieza', error);
      this.toastr.error('Error adding part', 'Error');
    });
  }
}