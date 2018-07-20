import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '../../../../../../node_modules/@angular/material/dialog';

@Component({
  selector: 'app-delete-employee',
  templateUrl: './delete-employee.component.html',
  styleUrls: ['./delete-employee.component.css']
})
export class DeleteEmployeeComponent implements OnInit {

  firstName = '';
  lastName = '';

  data = {
    delete: false
  }

  constructor(
    private dialogRef: MatDialogRef<DeleteEmployeeComponent>,
    @Inject(MAT_DIALOG_DATA) {firstName,lastName}) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

  ngOnInit() {

  }


  confirm() {
    this.data["delete"] = true;
    this.dialogRef.close(this.data);
  }

  cancel() {
    this.data["delete"] = false;
    this.dialogRef.close(this.data);
  }
}
