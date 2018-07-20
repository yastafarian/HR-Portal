import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-change-manager',
  templateUrl: './change-manager.component.html',
  styleUrls: ['./change-manager.component.css']
})
export class ChangeManagerComponent implements OnInit {

  form: FormGroup;

  departmentName: String = '';

  constructor(
      private fb: FormBuilder,
      private dialogRef: MatDialogRef<ChangeManagerComponent>,
      @Inject(MAT_DIALOG_DATA) {currentManagerId, departmentName}) {
        this.departmentName = departmentName;
        this.form = this.fb.group({
            currentManagerId: [currentManagerId, Validators.required]
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
