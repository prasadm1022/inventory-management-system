import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CustomerSearchCriteria } from '../../models/criteria/customer-search-criteria';
import { Customer } from '../../models/dto/customer';
import { InventoryService } from '../../services/inventory.service';
import { OrderHistoryBtnCellRendererComponent } from '../order-history-btn-cell-renderer/order-history-btn-cell-renderer.component';

@Component({
  selector: 'app-customer-grid',
  templateUrl: './customer-grid.component.html'
})
export class CustomerGridComponent implements OnInit {
  modules: any[] = [];
  columnDefs: any[];
  defaultColDef: any;
  rowData: Customer[];
  rowSelection: string;
  frameworkComponents: any;

  private gridApi;
  private customerSearchCriteria: CustomerSearchCriteria;

  constructor(
    private http: HttpClient,
    private inventoryService: InventoryService
  ) {
    this.columnDefs = [
      {headerName: 'Customer Name', field: 'name', editable: true},
      {field: 'phoneNo', editable: true},
      {field: 'address', editable: true},
      {field: 'email', editable: true},
      {headerName: 'NIC', field: 'nic', editable: true},
      {headerName: 'Order History', cellRenderer: 'orderHistoryBtnCellRendererComponent'}
    ];

    this.defaultColDef = {
      sortable: true,
      filter: true
    };

    this.frameworkComponents = {
      orderHistoryBtnCellRendererComponent: OrderHistoryBtnCellRendererComponent
    };

    this.rowSelection = 'single';
  }

  ngOnInit(): void { }

  onGridReady(params): void {
    this.gridApi = params.api;

    this.inventoryService.getCustomers(new CustomerSearchCriteria()).subscribe(value => this.rowData = value);
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
    this.inventoryService.saveCustomer(event.data).subscribe();
  }
}
