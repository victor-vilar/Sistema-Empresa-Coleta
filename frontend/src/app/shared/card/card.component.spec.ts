import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardComponent } from './card.component';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';

describe('CardComponent', () => {
  let component: CardComponent;
  let fixture: ComponentFixture<CardComponent>;
  let el: DebugElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    el = fixture.debugElement;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render text inputs', () => {

    const message = {
      text:'Texto de teste',
      iconClass:'/fa e',
      mainInfo:'Teste de input',
      link:'/etc/usr'
    };

    component.link = message.link;
    component.iconClass = message.iconClass;
    component.mainInfo = message.mainInfo;
    component.text = message.text;

    fixture.detectChanges();

    let aElement = el.query(By.css('a'));
    let iElement = el.query(By.css('i'));
    let spanElement = el.query(By.css('span'));
    let hElement = el.query(By.css('h6'));
    
    expect(aElement.nativeElement.routerLink).toBe(message.link);
    expect(iElement.nativeElement.className).toBe(message.iconClass);
    expect(spanElement.nativeElement.innerText).toBe(message.mainInfo);
    expect(hElement.nativeElement.innerText).toBe(message.text);

  })
});
