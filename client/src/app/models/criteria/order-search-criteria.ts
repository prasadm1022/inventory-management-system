import { OrderStatus } from '../enum/order-status';
import { AbstractSearchCriteria } from './abstract-search-criteria';

export class OrderSearchCriteria extends AbstractSearchCriteria {
  ids: number[] = [];
  deliveryArea: string;
  deliveryService: string;
  dispatchDate: Date;
  status: OrderStatus;
}
