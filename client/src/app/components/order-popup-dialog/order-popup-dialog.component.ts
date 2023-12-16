import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { CustomerSearchCriteria } from '../../models/criteria/customer-search-criteria';
import { ItemSearchCriteria } from '../../models/criteria/item-search-criteria';
import { Customer } from '../../models/dto/customer';
import { Item } from '../../models/dto/item';
import { Order } from '../../models/dto/order';
import { InventoryService } from '../../services/inventory.service';

@Component({
  selector: 'app-order-popup-dialog',
  templateUrl: './order-popup-dialog.component.html'
})
export class OrderPopupDialogComponent implements OnInit {
  customers: Promise<Customer[]>;
  itemMap = new Map<Item, number>();

  constructor(
    private http: HttpClient,
    private inventoryService: InventoryService,
    @Inject(MAT_DIALOG_DATA) public order: Order
  ) {
    const customerSearchCriteria = new CustomerSearchCriteria();
    customerSearchCriteria.ids.push(this.order.customerId);
    this.customers = this.inventoryService.getCustomers(customerSearchCriteria).toPromise();

    const itemSearchCriteria = new ItemSearchCriteria();
    itemSearchCriteria.ids = Object.keys(this.order.items).map(itemId => Number(itemId));
    this.inventoryService.getItems(itemSearchCriteria).subscribe(items => {
      for (const item of items) {
        Object.entries(this.order.items)
              .filter(entry => item.id === Number(entry[0]))
              .forEach(entry => this.itemMap.set(item, Number(entry[1])));
      }
    });
  }

  ngOnInit(): void { }
}
