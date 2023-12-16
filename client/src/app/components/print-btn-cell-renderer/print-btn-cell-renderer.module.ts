import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { PrintBtnCellRendererComponent } from './print-btn-cell-renderer.component';

@NgModule({
  declarations: [
    PrintBtnCellRendererComponent
  ],
  exports: [
    PrintBtnCellRendererComponent
  ],
  imports: [
    CommonModule,
    MatButtonModule
  ]
})
export class PrintBtnCellRendererModule {}
