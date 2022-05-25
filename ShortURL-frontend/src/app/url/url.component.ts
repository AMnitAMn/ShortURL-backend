import { Component, OnInit } from '@angular/core';
import { UrlService } from './url.service';
import { ClipboardService } from 'ngx-clipboard';

@Component({
  selector: 'app-url',
  templateUrl: './url.component.html',
  styleUrls: ['./url.component.css']
})
export class UrlComponent implements OnInit {

  longURL:string = "";
  shortURL:string = ".....";
  constructor(private urlService:UrlService, private clipboardApi: ClipboardService) { 
  }

  ngOnInit(): void {
  }

  shortenURL(): void {
    this.urlService.shortenURL(this.longURL).subscribe((data: string) =>
    this.shortURL = data
    );
  }

  copyText() {
    this.clipboardApi.copyFromContent(this.shortURL)
  }

}
