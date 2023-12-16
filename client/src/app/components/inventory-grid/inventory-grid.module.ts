import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { AgGridModule } from 'ag-grid-angular';
import { InventoryGridComponent } from './inventory-grid.component';

@NgModule({
  declarations: [
    InventoryGridComponent
  ],
  exports: [
    InventoryGridComponent
  ],
  imports: [
    HttpClientModule,
    AgGridModule.withComponents([]),
    CommonModule
  ]
})
export class InventoryGridModule {}
