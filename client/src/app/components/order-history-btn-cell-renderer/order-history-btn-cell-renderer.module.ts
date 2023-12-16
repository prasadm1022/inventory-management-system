import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { OrderHistoryBtnCellRendererComponent } from './order-history-btn-cell-renderer.component';

@NgModule({
  declarations: [
    OrderHistoryBtnCellRendererComponent
  ],
  exports: [
    OrderHistoryBtnCellRendererComponent
  ],
  imports: [
    CommonModule,
    MatButtonModule
  ]
})
export class OrderHistoryBtnCellRendererModule {}
