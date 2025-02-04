import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudMenuComponent } from './crud-menu.component';
import { By } from '@angular/platform-browser';

fdescribe('CrudMenuComponent', () => {
  let component: CrudMenuComponent;
  let fixture: ComponentFixture<CrudMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CrudMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrudMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();


  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should add more links for each linksForOperation object ', () => {
    component.linksListForOperations.push({name:'link1',title:'link1',path:'/link1'});
    fixture.detectChanges();
    let linksList = fixture.debugElement.queryAll(By.css('a'));
    expect(linksList.length).toBe(1);

    component.linksListForOperations.push({name:'link2',title:'link2',path:'/link2'});
    component.linksListForOperations.push({name:'link3',title:'link3',path:'/link3'});
    fixture.detectChanges();
    linksList = fixture.debugElement.queryAll(By.css('a'));
    expect(linksList.length).toBe(3);

  })

  it('should render object info to links in the view',() => {
    component.linksListForOperations.push({name:'link1',title:'link1-title',path:'/link1'});
    fixture.detectChanges();
    let linksList = fixture.debugElement.queryAll(By.css('a'));
    expect(linksList.length).toBe(1);
    expect(linksList[0].nativeElement.routerLink).toBe(component.linksListForOperations[0].path);
    expect(linksList[0].nativeElement.title).toBe(component.linksListForOperations[0].title);
    expect(linksList[0].nativeElement.innerText).toBe(component.linksListForOperations[0].name);
  })


  
});
