import { Component, OnInit } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-svg-add-icon',
  templateUrl: './svg-add-icon.component.html',
  styleUrls: ['./svg-add-icon.component.css']
})
export class SvgAddIconComponent implements OnInit {

  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    // Note that we provide the icon here as a string literal here due to a limitation in
    // Stackblitz. If you want to provide the icon from a URL, you can use:
    // `iconRegistry.addSvgIcon('thumbs-up', sanitizer.bypassSecurityTrustResourceUrl('icon.svg'));`
    iconRegistry.addSvgIconLiteral('addicon', sanitizer.bypassSecurityTrustHtml(`<svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#262927"><path d="M450-450H220v-60h230v-230h60v230h230v60H510v230h-60v-230Z"/></svg>`));
  }

  ngOnInit(): void {
  }

}
