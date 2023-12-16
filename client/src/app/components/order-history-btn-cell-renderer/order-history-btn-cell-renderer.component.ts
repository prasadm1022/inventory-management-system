import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { IAfterGuiAttachedParams, ICellRendererParams } from 'ag-grid-community';
import { OrderHistoryPopupDialogComponent } from '../order-history-popup-dialog/order-history-popup-dialog.component';

@Component({
  selector: 'app-order-history-btn-cell-renderer',
  templateUrl: './order-history-btn-cell-renderer.component.html'
})
export class OrderHistoryBtnCellRendererComponent implements OnInit, ICellRendererAngularComp {
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
    this.dialog.open(OrderHistoryPopupDialogComponent, {data: this.params.data});
  }
}
