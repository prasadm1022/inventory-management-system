import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Customer } from '../../models/dto/customer';
import { InventoryService } from '../../services/inventory.service';

@Component({
  selector: 'app-add-customer-popup-dialog',
  templateUrl: './add-customer-popup-dialog.component.html'
})
export class AddCustomerPopupDialogComponent implements OnInit {
  requestFailed = false;

  constructor(
    private http: HttpClient,
    private inventoryService: InventoryService,
    private dialogRef: MatDialogRef<AddCustomerPopupDialogComponent>
  ) { }

  ngOnInit(): void { }

  onAddClick(data): void {
    if (data.name !== '' && data.address !== '' && data.phoneNo !== '') {
      const newCustomer = new Customer();
      newCustomer.name = data.name;
      newCustomer.address = data.address;
      newCustomer.phoneNo = data.phoneNo;
      newCustomer.nic = data.nic;
      newCustomer.email = data.email;

      this.inventoryService.saveCustomer(newCustomer)
          .subscribe(response => {
            if (response.length === 0) {
              this.requestFailed = true;
            } else {
              this.requestFailed = false;
              this.dialogRef.close();
              sessionStorage.setItem('selectedTab', '2');
              window.location.reload();
            }
          });
    }
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }
}
