import { SortDirection } from '../enum/sort-direction';

export class AbstractSearchCriteria {
  start: number;
  page: number;
  size: number;
  sortBy: string;
  sortDirection: SortDirection;
}
