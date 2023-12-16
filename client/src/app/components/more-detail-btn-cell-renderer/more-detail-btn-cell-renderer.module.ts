import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MoreDetailBtnCellRendererComponent } from './more-detail-btn-cell-renderer.component';

@NgModule({
  declarations: [
    MoreDetailBtnCellRendererComponent
  ],
  exports: [
    MoreDetailBtnCellRendererComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatDialogModule,
  ]
})
export class MoreDetailBtnCellRendererModule {}
