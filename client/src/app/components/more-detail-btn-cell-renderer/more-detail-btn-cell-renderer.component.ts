import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { IAfterGuiAttachedParams, ICellRendererParams } from 'ag-grid-community';
import { OrderPopupDialogComponent } from '../order-popup-dialog/order-popup-dialog.component';

@Component({
  selector: 'app-more-detail-btn-cell-renderer',
  templateUrl: './more-detail-btn-cell-renderer.component.html'
})
export class MoreDetailBtnCellRendererComponent implements OnInit, ICellRendererAngularComp {
  private params: any;

  constructor(
    private dialog: MatDialog
  ) { }

  public ngOnInit(): void { }

  agInit(params: any): void {
    this.params = params;
  }

  public afterGuiAttached(params?: IAfterGuiAttachedParams): void { }

  public refresh(params: ICellRendererParams): boolean {
    return false;
  }

  btnClickedHandler($event): void {
    this.dialog.open(OrderPopupDialogComponent, {data: this.params.data});
  }
}
