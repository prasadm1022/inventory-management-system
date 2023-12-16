import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { OrderSearchCriteria } from '../../models/criteria/order-search-criteria';
import { Order } from '../../models/dto/order';
import { DeliveryArea } from '../../models/enum/delivery-area';
import { DeliveryService } from '../../models/enum/delivery-service';
import { OrderStatus } from '../../models/enum/order-status';
import { InventoryService } from '../../services/inventory.service';
import { MoreDetailBtnCellRendererComponent } from '../more-detail-btn-cell-renderer/more-detail-btn-cell-renderer.component';
import { PrintBtnCellRendererComponent } from '../print-btn-cell-renderer/print-btn-cell-renderer.component';

@Component({
  selector: 'app-order-grid',
  templateUrl: './order-grid.component.html'
})
export class OrderGridComponent implements OnInit {
  modules: any[] = [];
  columnDefs: any[];
  defaultColDef: any;
  rowData: Order[];
  rowSelection: string;
  frameworkComponents: any;

  private gridApi;

  constructor(
    private http: HttpClient,
    private inventoryService: InventoryService
  ) {
    const deliveryAreas = Object.keys(DeliveryArea).filter(value => isNaN(Number(value)) === false).map(key => DeliveryArea[key]);
    const deliveryServices = Object.keys(DeliveryService).filter(value => isNaN(Number(value)) === false).map(key => DeliveryService[key]);
    const orderStatus = Object.keys(OrderStatus).filter(value => isNaN(Number(value)) === false).map(key => OrderStatus[key]);

    this.columnDefs = [
      {headerName: 'Order ID', field: 'id'},
      {field: 'deliveryService', editable: true, cellEditor: 'agSelectCellEditor', cellEditorParams: {values: deliveryServices}},
      {field: 'deliveryArea', editable: true, cellEditor: 'agSelectCellEditor', cellEditorParams: {values: deliveryAreas}},
      {
        headerName: 'Order Date',
        field: 'dispatchDate',
        cellRenderer: (data) => data.value ? (new Date(data.value)).toLocaleDateString() : ''
      },
      {field: 'totalPrice', editable: true, cellRenderer: (data) => data.value ? 'Rs. ' + data.value : ''},
      {field: 'status', editable: true, cellEditor: 'agSelectCellEditor', cellEditorParams: {values: orderStatus}},
      {headerName: 'More Details', cellRenderer: 'moreDetailBtnCellRendererComponent'},
      {headerName: 'Receipt', field: '', cellRenderer: 'printBtnCellRendererComponent'}
    ];

    this.defaultColDef = {
      sortable: true,
      filter: true
    };

    this.frameworkComponents = {
      moreDetailBtnCellRendererComponent: MoreDetailBtnCellRendererComponent,
      printBtnCellRendererComponent: PrintBtnCellRendererComponent
    };

    this.rowSelection = 'single';
  }

  ngOnInit(): void { }

  onGridReady(params): void {
    this.gridApi = params.api;

    this.inventoryService.getOrders(new OrderSearchCriteria()).subscribe(orders => this.rowData = orders);
  }

  onFirstDataRendered(params): void {
    this.gridApi = params.api;
    params.columnApi.autoSizeAllColumns();
  }

  onSelectionChanged($event: any): void {
    const selectedRows = this.gridApi.getSelectedRows();
    console.log(selectedRows);
  }

  onCellValueChanged(event): void {
    this.inventoryService.saveOrder(event.data).subscribe();
  }
}
