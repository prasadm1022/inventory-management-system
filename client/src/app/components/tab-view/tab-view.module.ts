import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatIconModule } from '@angular/material/icon';
import { MatTabsModule } from '@angular/material/tabs';
import { CustomerGridModule } from '../customer-grid/customer-grid.module';
import { InventoryGridModule } from '../inventory-grid/inventory-grid.module';
import { OrderGridModule } from '../order-grid/order-grid.module';
import { TabViewComponent } from './tab-view.component';

@NgModule({
  declarations: [
    TabViewComponent
  ],
  exports: [
    TabViewComponent
  ],
  imports: [
    CommonModule,
    MatButtonToggleModule,
    MatTabsModule,
    OrderGridModule,
    InventoryGridModule,
    CustomerGridModule,
    MatIconModule,
    MatButtonModule
  ]
})
export class TabViewModule {}
