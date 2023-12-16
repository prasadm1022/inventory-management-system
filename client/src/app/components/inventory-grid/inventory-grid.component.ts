import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ItemSearchCriteria } from '../../models/criteria/item-search-criteria';
import { Item } from '../../models/dto/item';
import { InventoryService } from '../../services/inventory.service';

@Component({
  selector: 'app-inventory-grid',
  templateUrl: './inventory-grid.component.html'
})
export class InventoryGridComponent implements OnInit {
  modules: any[] = [];
  columnDefs: any[];
  defaultColDef: any;
  rowData: Item[];
  rowSelection: string;

  private gridApi;
  private itemSearchCriteria: ItemSearchCriteria;

  constructor(
    private http: HttpClient,
    private inventoryService: InventoryService
  ) {
    this.columnDefs = [
      {headerName: 'Item Code', field: 'code'},
      {field: 'name'},
      {field: 'brand'},
      {field: 'quantity'},
      {field: 'unitPrice', cellRenderer: (data) => data.value ? 'Rs. ' + data.value : ''},
      {field: 'specialNotes'}
    ];

    this.defaultColDef = {
      sortable: true,
      filter: true,
      editable: true
    };

    this.rowSelection = 'single';
  }

  ngOnInit(): void { }

  onGridReady(params): void {
    this.gridApi = params.api;

    this.inventoryService.getItems(new ItemSearchCriteria()).subscribe(items => this.rowData = items);
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
    this.inventoryService.saveItem(event.data).subscribe();
  }
}
