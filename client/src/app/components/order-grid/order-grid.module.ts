import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { AgGridModule } from 'ag-grid-angular';
import { MoreDetailBtnCellRendererComponent } from '../more-detail-btn-cell-renderer/more-detail-btn-cell-renderer.component';
import { PrintBtnCellRendererComponent } from '../print-btn-cell-renderer/print-btn-cell-renderer.component';
import { OrderGridComponent } from './order-grid.component';

@NgModule({
  declarations: [
    OrderGridComponent
  ],
  exports: [
    OrderGridComponent
  ],
  imports: [
    HttpClientModule,
    AgGridModule.withComponents([
      MoreDetailBtnCellRendererComponent,
      PrintBtnCellRendererComponent
    ]),
    CommonModule
  ]
})
export class OrderGridModule {}
