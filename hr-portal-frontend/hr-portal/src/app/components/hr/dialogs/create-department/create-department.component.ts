import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Department } from '../../../../models/department';
import { FormBuilder, Validators, FormGroup } from "@angular/forms";


@Component({
  selector: 'app-create-department',
  templateUrl: './create-department.component.html',
  styleUrls: ['./create-department.component.css']
})
export class CreateDepartmentComponent implements OnInit {

  form: FormGroup;

  constructor(
      private fb: FormBuilder,
      private dialogRef: MatDialogRef<CreateDepartmentComponent>,
      @Inject(MAT_DIALOG_DATA) {name,number, currentManager}:Department ) {

      this.form = this.fb.group({
          name: [name, Validators.required],
          number: [number, Validators.required],
          currentManager: ["", Validators.required]
      });
  }

  ngOnInit() {

  }


  save() {
      this.dialogRef.close(this.form.value);
  }

  close() {
      this.dialogRef.close();
  }

}
