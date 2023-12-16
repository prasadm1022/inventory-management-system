import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { OrderSearchCriteria } from '../../models/criteria/order-search-criteria';
import { Customer } from '../../models/dto/customer';
import { Order } from '../../models/dto/order';
import { InventoryService } from '../../services/inventory.service';
import { PrintBtnCellRendererComponent } from '../print-btn-cell-renderer/print-btn-cell-renderer.component';

@Component({
  selector: 'app-order-history-popup-dialog',
  templateUrl: './order-history-popup-dialog.component.html'
})
export class OrderHistoryPopupDialogComponent implements OnInit {
  modules: any[] = [];
  columnDefs: any[];
  defaultColDef: any;
  rowData: Order[] = [];
  rowSelection: string;
  frameworkComponents: any;

  private gridApi;

  constructor(
    private http: HttpClient,
    private inventoryService: InventoryService,
    @Inject(MAT_DIALOG_DATA) public customer: Customer
  ) {
    this.columnDefs = [
      {headerName: 'Order ID', field: 'id'},
      {field: 'deliveryService'},
      {field: 'deliveryArea'},
      {field: 'dispatchDate', cellRenderer: (data) => data.value ? (new Date(data.value)).toLocaleDateString() : ''},
      {field: 'totalPrice', cellRenderer: (data) => data.value ? 'Rs. ' + data.value : ''},
      {field: 'status'},
      {headerName: 'Receipt', field: '', cellRenderer: 'printBtnCellRendererComponent'}
    ];

    this.defaultColDef = {
      sortable: true,
      filter: true
    };

    this.frameworkComponents = {
      printBtnCellRendererComponent: PrintBtnCellRendererComponent
    };

    this.rowSelection = 'single';
  }

  ngOnInit(): void { }

  onGridReady(params): void {
    this.gridApi = params.api;

    const orderSearchCriteria = new OrderSearchCriteria();
    orderSearchCriteria.ids = this.customer.orderIdList.map(id => id);
    if (orderSearchCriteria.ids.length === 0) {
      this.rowData = [];
    } else {
      this.inventoryService.getOrders(orderSearchCriteria).subscribe(orders => this.rowData = orders);
    }
  }

  onFirstDataRendered(params): void {
    this.gridApi = params.api;
    params.columnApi.autoSizeAllColumns();
  }

  onSelectionChanged($event: any): void {
    const selectedRows = this.gridApi.getSelectedRows();
    console.log(selectedRows);
  }
}
