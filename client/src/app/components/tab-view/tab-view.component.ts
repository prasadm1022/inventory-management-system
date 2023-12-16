import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddCustomerPopupDialogComponent } from '../add-customer-popup-dialog/add-customer-popup-dialog.component';
import { AddItemPopupDialogComponent } from '../add-item-popup-dialog/add-item-popup-dialog.component';
import { AddOrderPopupDialogComponent } from '../add-order-popup-dialog/add-order-popup-dialog.component';

@Component({
  selector: 'app-tab-view',
  templateUrl: './tab-view.component.html'
})
export class TabViewComponent implements OnInit {
  NAME_ORDERS = 'Orders';
  NAME_INVENTORY = 'Inventory';
  NAME_CUSTOMERS = 'Customers';

  selectOrder = true;
  selectInventory = false;
  selectCustomer = false;
  selectedTabIndex = 0;

  constructor(
    private dialog: MatDialog
  ) {
    this.selectedTabIndex = sessionStorage.getItem('selectedTab') == null
      ? 0
      : Number(sessionStorage.getItem('selectedTab'));
    this.onSelectTabAfterRefresh(this.selectedTabIndex);
  }

  ngOnInit(): void { }

  onSelectedTabChange($event): void {
    switch ($event.tab.textLabel) {
      case this.NAME_ORDERS:
        this.selectOrder = true;
        this.selectInventory = false;
        this.selectCustomer = false;
        sessionStorage.setItem('selectedTab', '0');
        break;
      case this.NAME_INVENTORY:
        this.selectOrder = false;
        this.selectInventory = true;
        this.selectCustomer = false;
        sessionStorage.setItem('selectedTab', '1');
        break;
      case this.NAME_CUSTOMERS:
        this.selectOrder = false;
        this.selectInventory = false;
        this.selectCustomer = true;
        sessionStorage.setItem('selectedTab', '2');
        break;
      default:
        this.selectOrder = true;
        this.selectInventory = false;
        this.selectCustomer = false;
        sessionStorage.setItem('selectedTab', '0');
        break;
    }
  }

  onSelectTabAfterRefresh(index: number): void {
    switch (index) {
      case 0:
        this.selectOrder = true;
        this.selectInventory = false;
        this.selectCustomer = false;
        break;
      case 1:
        this.selectOrder = false;
        this.selectInventory = true;
        this.selectCustomer = false;
        break;
      case 2:
        this.selectOrder = false;
        this.selectInventory = false;
        this.selectCustomer = true;
        break;
      default:
        this.selectOrder = true;
        this.selectInventory = false;
        this.selectCustomer = false;
        break;
    }
  }

  onAddButtonClick($event, type: string): void {
    if (type === this.NAME_ORDERS) {
      this.dialog.open(AddOrderPopupDialogComponent);
    } else if (type === this.NAME_INVENTORY) {
      this.dialog.open(AddItemPopupDialogComponent);
    } else if (type === this.NAME_CUSTOMERS) {
      this.dialog.open(AddCustomerPopupDialogComponent);
    }
  }
}
