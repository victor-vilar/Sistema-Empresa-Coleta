import { ComponentFixture, fakeAsync, TestBed, tick, waitForAsync } from '@angular/core/testing';

import { CrudMenuComponent } from './crud-menu.component';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { Location, CommonModule } from '@angular/common';
import { TestModule } from 'src/app/tests/tests.module';
import { MockTestComponent } from 'src/app/tests/mock-test.component';



describe('CrudMenuComponent', () => {
  let component: CrudMenuComponent;
  let fixture: ComponentFixture<CrudMenuComponent>;
  let router:Router;
  let location:Location

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CrudMenuComponent, MockTestComponent ],
      providers:[Location],
      imports:[CommonModule,TestModule,RouterTestingModule.withRoutes([
        {path:'link1',component: MockTestComponent}
      ])]
    }).compileComponents()
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CrudMenuComponent);
    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
    component = fixture.componentInstance;
    router.initialNavigation();
    fixture.detectChanges();

  })

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
    expect(linksList[0].nativeElement.getAttribute('href')).toEqual(component.linksListForOperations[0].path + "?dialog=true");
    expect(linksList[0].nativeElement.title).toBe(component.linksListForOperations[0].title);
    expect(linksList[0].nativeElement.innerText).toBe(component.linksListForOperations[0].name);
  })

  it('should navigate to the component of the link ',fakeAsync(() => {
    
    component.linksListForOperations.push({name:'link1',title:'link1-title',path:'link1'});
    fixture.detectChanges();

    let bt = fixture.debugElement.queryAll(By.css('a'));
    expect(bt[0]).toBeTruthy();
    
    
    spyOn(router,'navigateByUrl');
    bt[0].triggerEventHandler('click',null);

    router.navigateByUrl('link1?dialog=true');
    tick();
    fixture.detectChanges();
    
    fixture.whenStable().then(() => {
      console.log('current url:' + location.path());
      expect(router.navigateByUrl).toHaveBeenCalledTimes(1);
      expect(location.path()).toBe("link1?dialog=true") ;
    })
    
    
    


  }))


  
});
