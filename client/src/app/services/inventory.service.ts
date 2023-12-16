import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { CustomerSearchCriteria } from '../models/criteria/customer-search-criteria';
import { ItemSearchCriteria } from '../models/criteria/item-search-criteria';
import { OrderSearchCriteria } from '../models/criteria/order-search-criteria';
import { Customer } from '../models/dto/customer';
import { Item } from '../models/dto/item';
import { Order } from '../models/dto/order';
import { ResponseWrapper } from '../models/response/response-wrapper';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  private BASE_URL = 'http://localhost:8080/inventory-service';
  private API_VERSION = 'v1';
  private ITEM_ENDPOINT = 'item';
  private CUSTOMER_ENDPOINT = 'customer';
  private ORDER_ENDPOINT = 'order';

  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {}

  public getOrders(criteria: OrderSearchCriteria): Observable<Order[]> {
    const url = this.BASE_URL + '/' + this.API_VERSION + '/' + this.ORDER_ENDPOINT;
    const params = new HttpParams({
      fromObject: {
        ids: criteria.ids === undefined ? [] : criteria.ids.toString()
        // TODO : add other parameters when needed
      }
    });
    return this.http.get<ResponseWrapper<Order>>(url, {params}).pipe(
      map(response => response.data),
      catchError(error => this.handleError<Order>(error))
    );
  }

  public saveOrder(order: Order): Observable<Order[]> {
    const url = this.BASE_URL + '/' + this.API_VERSION + '/' + this.ORDER_ENDPOINT;
    return this.http.post<ResponseWrapper<Order>>(url, order, this.httpOptions).pipe(
      map(response => response.data),
      catchError(error => this.handleError<Order>(error))
    );
  }

  public deleteOrder(id: number): void {
    const url = this.BASE_URL + '/' + this.API_VERSION + '/' + this.ORDER_ENDPOINT + '?id=' + id;
    // TODO
  }

  public getItems(criteria: ItemSearchCriteria): Observable<Item[]> {
    const url = this.BASE_URL + '/' + this.API_VERSION + '/' + this.ITEM_ENDPOINT;
    const params = new HttpParams({
      fromObject: {
        ids: criteria.ids === undefined ? [] : criteria.ids.toString()
        // TODO : add other parameters when needed
      }
    });
    return this.http.get<ResponseWrapper<Item>>(url, {params}).pipe(
      map(response => response.data),
      catchError(error => this.handleError<Item>(error))
    );
  }

  public saveItem(item: Item): Observable<Item[]> {
    const url = this.BASE_URL + '/' + this.API_VERSION + '/' + this.ITEM_ENDPOINT;
    return this.http.post<ResponseWrapper<Item>>(url, item, this.httpOptions).pipe(
      map(response => response.data),
      catchError(error => this.handleError<Item>(error))
    );
  }

  public deleteItem(id: number): void {
    const url = this.BASE_URL + '/' + this.API_VERSION + '/' + this.ITEM_ENDPOINT + '?id=' + id;
    // TODO
  }

  public getCustomers(criteria: CustomerSearchCriteria): Observable<Customer[]> {
    const url = this.BASE_URL + '/' + this.API_VERSION + '/' + this.CUSTOMER_ENDPOINT;
    const params = new HttpParams({
      fromObject: {
        ids: criteria.ids === undefined ? [] : criteria.ids.toString(),
        phoneNo: criteria.phoneNo === undefined ? '0' : criteria.phoneNo.toString()
        // TODO : add other parameters when needed
      }
    });
    return this.http.get<ResponseWrapper<Customer>>(url, {params}).pipe(
      map(response => response.data),
      catchError(error => this.handleError<Customer>(error))
    );
  }

  public saveCustomer(customer: Customer): Observable<Customer[]> {
    const url = this.BASE_URL + '/' + this.API_VERSION + '/' + this.CUSTOMER_ENDPOINT;
    return this.http.post<ResponseWrapper<Customer>>(url, customer, this.httpOptions).pipe(
      map(response => response.data),
      catchError(error => this.handleError<Customer>(error))
    );
  }

  public deleteCustomer(id: number): void {
    const url = this.BASE_URL + '/' + this.API_VERSION + '/' + this.CUSTOMER_ENDPOINT + '?id=' + id;
    // TODO
  }

  handleError<T>(error): Observable<T[]> {
    console.log(error);
    return of([]);
  }
}
