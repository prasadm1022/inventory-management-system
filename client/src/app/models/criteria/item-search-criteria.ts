import { AbstractSearchCriteria } from './abstract-search-criteria';

export class ItemSearchCriteria extends AbstractSearchCriteria {
  ids: number[] = [];
  code: string;
  name: string;
  brand: string;
}
