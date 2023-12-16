import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { AgGridModule } from 'ag-grid-angular';
import { CustomerGridComponent } from './customer-grid.component';

@NgModule({
  declarations: [
    CustomerGridComponent
  ],
  exports: [
    CustomerGridComponent
  ],
  imports: [
    HttpClientModule,
    AgGridModule.withComponents([]),
    CommonModule
  ]
})
export class CustomerGridModule {}
