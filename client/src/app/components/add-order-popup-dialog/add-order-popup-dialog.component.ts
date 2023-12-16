import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { CustomerSearchCriteria } from '../../models/criteria/customer-search-criteria';
import { ItemSearchCriteria } from '../../models/criteria/item-search-criteria';
import { Customer } from '../../models/dto/customer';
import { Item } from '../../models/dto/item';
import { Order } from '../../models/dto/order';
import { DeliveryArea } from '../../models/enum/delivery-area';
import { DeliveryService } from '../../models/enum/delivery-service';
import { InventoryService } from '../../services/inventory.service';

@Component({
  selector: 'app-add-order-popup-dialog',
  templateUrl: './add-order-popup-dialog.component.html'
})
export class AddOrderPopupDialogComponent implements OnInit {
  requestFailed = false;
  invalidCustomer = false;
  deliveryAreas: string[];
  deliveryServices: string[];
  deliveryCharges = 200; // TODO : cal delivery charge according to the delivery area
  totalPrice = 0;
  customers: Promise<Customer[]>;
  items: Promise<Item[]>;
  orderItems = new Map<number, number>();
  displayItems = new Map<string, number>();

  constructor(
    private http: HttpClient,
    private inventoryService: InventoryService,
    private dialogRef: MatDialogRef<AddOrderPopupDialogComponent>
  ) {
    this.items = inventoryService.getItems(new ItemSearchCriteria()).toPromise();

    this.deliveryAreas = Object.keys(DeliveryArea)
                               .filter(key => isNaN(Number(key)) === false)
                               .map(key => DeliveryArea[key]);

    this.deliveryServices = Object.keys(DeliveryService)
                                  .filter(key => isNaN(Number(key)) === false)
                                  .map(key => DeliveryService[key]);
  }

  ngOnInit(): void { }

  onPhoneNoTextChange($event): void {
    const criteria = new CustomerSearchCriteria();
    criteria.phoneNo = $event.target.value;
    this.customers = this.inventoryService.getCustomers(criteria).toPromise();
  }

  onAddClick(data): void {
    if (data.phoneNo !== '' && data.deliveryArea !== '' && data.deliveryService !== '') {
      const customerSearchCriteria = new CustomerSearchCriteria();
      customerSearchCriteria.phoneNo = data.phoneNo;
      this.inventoryService.getCustomers(customerSearchCriteria).subscribe(customers => {
        if (customers.length === 1) {
          this.invalidCustomer = false;

          const newOrder = new Order();
          newOrder.deliveryService = data.deliveryService;
          newOrder.deliveryArea = data.deliveryArea;
          newOrder.totalPrice = this.totalPrice + this.deliveryCharges;
          newOrder.dispatchDate = new Date();
          newOrder.customerId = customers[0].id;
          this.orderItems.forEach((value, key) => newOrder.items[key] = value);

          this.inventoryService.saveOrder(newOrder).subscribe(response => {
            if (response.length === 0) {
              this.requestFailed = true;
            } else {
              this.requestFailed = false;
              this.dialogRef.close();
              sessionStorage.setItem('selectedTab', '0');
              window.location.reload();
            }
          });
        } else {
          this.invalidCustomer = true;
        }
      });
    }
  }

  onAddItemClick(data): void {
    if (data.item !== '' && data.quantity !== '') {
      const itemId = Number(data.item);

      if (this.orderItems.has(itemId)) {
        const existingCount = this.orderItems.get(itemId);
        this.orderItems.set(itemId, existingCount + data.quantity);
      } else {
        this.orderItems.set(itemId, data.quantity);
      }

      this.items.then(response => {
        response.filter(val => val.id === itemId).forEach(val => {
          this.displayItems.set(val.name, this.orderItems.get(itemId));
          this.totalPrice = this.totalPrice + (data.quantity * val.unitPrice);
        });
      });
    }
  }

  onResetItemsClick($event): void {
    this.totalPrice = 0;
    this.orderItems.clear();
    this.displayItems.clear();
  }

  onCancelClick($event): void {
    this.dialogRef.close();
  }
}
