import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AgGridModule } from 'ag-grid-angular';
import { AppComponent } from './app.component';
import { FooterModule } from './components/footer/footer.module';
import { HeaderModule } from './components/header/header.module';
import { OrderGridModule } from './components/order-grid/order-grid.module';
import { OrderHistoryPopupDialogComponent } from './components/order-history-popup-dialog/order-history-popup-dialog.component';
import { OrderPopupDialogComponent } from './components/order-popup-dialog/order-popup-dialog.component';
import { TabViewModule } from './components/tab-view/tab-view.module';
import { PrintPopupDialogComponent } from './components/print-popup-dialog/print-popup-dialog.component';
import { AddOrderPopupDialogComponent } from './components/add-order-popup-dialog/add-order-popup-dialog.component';
import { AddItemPopupDialogComponent } from './components/add-item-popup-dialog/add-item-popup-dialog.component';
import { AddCustomerPopupDialogComponent } from './components/add-customer-popup-dialog/add-customer-popup-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    OrderPopupDialogComponent,
    PrintPopupDialogComponent,
    AddOrderPopupDialogComponent,
    AddItemPopupDialogComponent,
    AddCustomerPopupDialogComponent,
    OrderHistoryPopupDialogComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    MatDialogModule,
    BrowserAnimationsModule,
    HeaderModule,
    FooterModule,
    TabViewModule,
    MatButtonModule,
    MatInputModule,
    FormsModule,
    MatIconModule,
    MatSelectModule,
    MatAutocompleteModule,
    OrderGridModule,
    AgGridModule
  ],
  providers: [],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {}
