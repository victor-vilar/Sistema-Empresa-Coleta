import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-svg-list-icon',
  templateUrl: './svg-list-icon.component.html',
  styleUrls: ['./svg-list-icon.component.css']
})
export class SvgListIconComponent implements OnInit, OnChanges {



  @Input()
  badgeSize:number;

  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    // Note that we provide the icon here as a string literal here due to a limitation in
    // Stackblitz. If you want to provide the icon from a URL, you can use:
    // `iconRegistry.addSvgIcon('thumbs-up', sanitizer.bypassSecurityTrustResourceUrl('icon.svg'));`
    iconRegistry.addSvgIconLiteral('mylist', sanitizer.bypassSecurityTrustHtml(`<svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#30393e"><path d="M293.08-597.69v-60H820v60H293.08Zm0 147.69v-60H820v60H293.08Zm0 147.69v-60H820v60H293.08ZM172.31-595.38q-13.73 0-23.02-9.4t-9.29-23.3q0-13.56 9.29-22.74 9.29-9.18 23.02-9.18t23.02 9.18q9.29 9.18 9.29 22.74 0 13.9-9.29 23.3t-23.02 9.4Zm0 147.3q-13.73 0-23.02-9.18Q140-466.43 140-480q0-14.31 9.29-23.5t23.02-9.19q13.73 0 23.02 9.19t9.29 23.5q0 13.57-9.29 22.74-9.29 9.18-23.02 9.18Zm0 148.08q-13.73 0-23.02-9.4T140-332.69q0-13.57 9.29-22.75t23.02-9.18q13.73 0 23.02 9.18t9.29 22.75q0 13.89-9.29 23.29-9.29 9.4-23.02 9.4Z"/></svg>`));
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.badgeSize = changes['badgeSize'].currentValue;
  }

  ngOnInit(): void {
  }

}
