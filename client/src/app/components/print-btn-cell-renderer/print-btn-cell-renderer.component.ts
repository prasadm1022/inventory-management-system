import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { IAfterGuiAttachedParams, ICellRendererParams } from 'ag-grid-community';
import { PrintPopupDialogComponent } from '../print-popup-dialog/print-popup-dialog.component';

@Component({
  selector: 'app-print-btn-cell-renderer',
  templateUrl: './print-btn-cell-renderer.component.html'
})
export class PrintBtnCellRendererComponent implements OnInit, ICellRendererAngularComp {
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
    this.dialog.open(PrintPopupDialogComponent, {data: this.params.data});
  }
}
