import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class UrlService {

  shortURL:string="";
  constructor(private httpClient:HttpClient) { }
  private url = 'http://localhost:8080/shortener';

  shortenURL(longURL:string):any{
    var headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    return this.httpClient.post(this.url, longURL, { headers, responseType: 'text' as 'json' });
  }

  
}
