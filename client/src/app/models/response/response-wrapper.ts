import { PageData } from './page-data';
import { ResponseWrapperError } from './response-wrapper-error';
import { ResponseWrapperStatus } from './response-wrapper-status';

export class ResponseWrapper<T> {
  static SUCCESS: number;
  static ERROR: number;
  static WARNING: number;

  apiVersion: string;
  status: ResponseWrapperStatus;
  error: ResponseWrapperError;
  data: Array<T>;
  pageData: PageData;
  metaData: Map<string, string>;
}
