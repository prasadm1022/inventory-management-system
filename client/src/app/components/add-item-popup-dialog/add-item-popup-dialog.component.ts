import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Item } from '../../models/dto/item';
import { InventoryService } from '../../services/inventory.service';

@Component({
  selector: 'app-add-item-popup-dialog',
  templateUrl: './add-item-popup-dialog.component.html'
})
export class AddItemPopupDialogComponent implements OnInit {
  requestFailed = false;

  constructor(
    private http: HttpClient,
    private inventoryService: InventoryService,
    private dialogRef: MatDialogRef<AddItemPopupDialogComponent>
  ) { }

  ngOnInit(): void { }

  onAddClick(data): void {
    if (data.code !== '' && data.name !== '' && data.brand !== '' && data.unitPrice !== '' && data.quantity !== '') {
      const newItem = new Item();
      newItem.code = data.code;
      newItem.name = data.name;
      newItem.quantity = data.quantity;
      newItem.unitPrice = data.unitPrice;
      newItem.brand = data.brand;
      newItem.specialNotes = data.specialNotes;

      this.inventoryService.saveItem(newItem)
          .subscribe(response => {
            if (response.length === 0) {
              this.requestFailed = true;
            } else {
              this.requestFailed = false;
              this.dialogRef.close();
              sessionStorage.setItem('selectedTab', '1');
              window.location.reload();
            }
          });
    }
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }
}
