import { Component, OnInit } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-svg-save-icon',
  templateUrl: './svg-save-icon.component.html',
  styleUrls: ['./svg-save-icon.component.css']
})
export class SvgSaveIconComponent implements OnInit {

  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    // Note that we provide the icon here as a string literal here due to a limitation in
    // Stackblitz. If you want to provide the icon from a URL, you can use:
    // `iconRegistry.addSvgIcon('thumbs-up', sanitizer.bypassSecurityTrustResourceUrl('icon.svg'));`
    iconRegistry.addSvgIconLiteral('save-icon', sanitizer.bypassSecurityTrustHtml(`<svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#30393e"><path d="M212.31-140q-29.92 0-51.12-21.19Q140-182.39 140-212.31v-535.38q0-29.92 21.19-51.12Q182.39-820 212.31-820h459.23L820-671.54v196.62q-14.39-5.7-29.69-7.23-15.31-1.54-30.31.3v-164.77L646.62-760H212.31q-5.39 0-8.85 3.46t-3.46 8.85v535.38q0 5.39 3.46 8.85t8.85 3.46h224.61v60H212.31ZM200-760v560-560ZM524.62-60v-105.69l217.15-216.16q7.46-7.46 16.15-10.5 8.69-3.03 17.39-3.03 9.3 0 18.19 3.53 8.88 3.54 15.96 10.62l37 37.38q6.46 7.47 10 16.16Q860-319 860-310.31t-3.23 17.69q-3.23 9-10.31 16.46L630.31-60H524.62Zm287.69-250.31-37-37.38 37 37.38Zm-240 202.62h38l129.84-130.47-18.38-19-18.62-18.76-130.84 130.23v38Zm149.46-149.47-18.62-18.76 37 37.76-18.38-19ZM255.39-564.62h328.45v-139.99H255.39v139.99ZM480-269.23q4.08 0 7.77-.77 3.69-.77 7.38-2.69l81.39-80.39q1.92-4.3 2.69-7.88t.77-8.27q0-41.54-29.23-70.77-29.23-29.23-70.77-29.23-41.54 0-70.77 29.23Q380-410.77 380-369.23q0 41.54 29.23 70.77 29.23 29.23 70.77 29.23Z"/></svg>`));
  }

  ngOnInit(): void {
  }

}
