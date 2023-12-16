import { OrderStatus } from '../enum/order-status';

export class Order {
  id: number;
  totalPrice: number;
  deliveryArea: string;
  deliveryService: string;
  dispatchDate: Date;
  status: OrderStatus;
  customerId: number;
  items = {};
}
