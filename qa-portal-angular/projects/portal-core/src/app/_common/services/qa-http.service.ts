import { Injectable, ErrorHandler } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { endpoints, EndpointRef } from '../../../environments/endpoints';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { QaErrorHandlerService } from './qa-error-handler.service';

export interface HttpOptions {
  handleErrors?: true;
  headers?: HttpHeaders | {
    [header: string]: string | string[];
  };
  params?: HttpParams | {
    [param: string]: string | string[];
  };
}

export interface HttpUrlDefinition {
  ref: EndpointRef;
  params?: { [key: string]: any };
}

@Injectable({
  providedIn: 'root'
})
export class QaHttpService {

  /** Default set of Http request options. This is typically merged with incoming options which can override it. */
  private DEFAULT_HTTP_OPTIONS = {
    headers: { 'Content-Type': 'application/json' }
  };

  constructor(private httpClient: HttpClient) { }

  /**
   * Constructs a formatted URL from a base URL in configuration (determined by url.ref) and params.
   *
   * @param url Argument can be raw string URL or URL definition object.
   *    If string is provided it is simply returned.
   *    If an object is provided the function will attempt to create a URL using the supplied ref and params.
   */
  private buildUrl(url: string | HttpUrlDefinition): string {
    if (typeof url === 'object') {
      let constructedUrl = endpoints[url.ref] || '/ERROR_NO_URL_FOUND';
      for (const key in url.params) {
        if (key !== undefined) {
          constructedUrl = constructedUrl.replace(`:${key}`, url.params[key]);
        }
      }
      return constructedUrl;
    }
    return url;
  }


  public get<T>(url: string | HttpUrlDefinition, options?: HttpOptions): Observable<T> {
    return this.httpClient.get<T>(this.buildUrl(url), { ...this.DEFAULT_HTTP_OPTIONS, ...options });
  }

  public post<T>(url: string | HttpUrlDefinition, body: any, options?: HttpOptions): Observable<T> {
    return this.httpClient.post<T>(this.buildUrl(url), body, { ...this.DEFAULT_HTTP_OPTIONS, ...options });
  }

  public put<T>(url: string | HttpUrlDefinition, body: any, options?: HttpOptions): Observable<T> {
    return this.httpClient.put<T>(this.buildUrl(url), body, { ...this.DEFAULT_HTTP_OPTIONS, ...options });
  }

  public patch<T>(url: string | HttpUrlDefinition, body: any, options?: HttpOptions): Observable<T> {
    return this.httpClient.patch<T>(this.buildUrl(url), body, { ...this.DEFAULT_HTTP_OPTIONS, ...options });
  }

  public delete<T>(url: string | HttpUrlDefinition, options?: HttpOptions): Observable<T> {
    return this.httpClient.delete<T>(this.buildUrl(url), { ...this.DEFAULT_HTTP_OPTIONS, ...options });
  }

}
