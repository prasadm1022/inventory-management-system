import { AbstractSearchCriteria } from './abstract-search-criteria';

export class CustomerSearchCriteria extends AbstractSearchCriteria {
  ids: number[] = [];
  phoneNo: number;
  name: string;
  email: string;
  nic: string;
}
