import { ComponentFixture, fakeAsync, TestBed, tick } from "@angular/core/testing";
import { ItensTableComponent } from "./itens-table.component";
import { SharedModule } from "../shared.module";
import { DialogServiceService } from "../services/dialog-service.service";
import { of } from "rxjs";
import { Mapper } from "../interfaces/mapper.mapper";
import { HttpResponse } from "@angular/common/http";




fdescribe('ItensTableComponent', () => {

      let component: ItensTableComponent;
      let fixture: ComponentFixture<ItensTableComponent>;
      let service:any;


      beforeEach(async() => {
        await TestBed.configureTestingModule({
            declarations: [ItensTableComponent],
            imports: [SharedModule],
            providers:[
                {provide:DialogServiceService,useValue:jasmine.createSpyObj(
                  'dialogService',{
                    'openProgressDialog':'',
                    'closeProgressSpinnerDialog':'',
                    'openErrorDialog':'',
                    'openConfirmationDialog':of(true)
                  }
                )}
                
            ]
        }).compileComponents();

        fixture = TestBed.createComponent(ItensTableComponent);
        component = fixture.componentInstance;
        

        service  = jasmine.createSpyObj('service',['getAll','refreshAllData','delete','getAllByCustomerId']);
        
        service.refreshAllData.and.returnValue(of([]));
        service.delete.and.returnValue(of(['true']));
        service.getAllByCustomerId.and.returnValue(of([{id:1,nome:'cliente1'}]));
        service.getAll.and.returnValue(of([{id:1,nome:'cliente1'},{id:2,nome:'cliente2'}]));
        
        component.service = service;
        
        
        
        fixture.detectChanges();
      })



      it('should test onInit method', () => {
        spyOn(component,'getAll');
        component.ngOnInit();
        expect(component).toBeTruthy();
        expect(component.getAll).toHaveBeenCalledTimes(1);

      })

      it('should test delete item method passing an object that have an id',(fakeAsync(() =>{
        let objectWithAnIdentification = {id: 1};
        spyOn(component,'getAll');
        component.deleteItem(objectWithAnIdentification);
        tick(50)
        expect(component.service.delete).toHaveBeenCalledTimes(1);
        expect(component.dialogService.openProgressDialog).toHaveBeenCalledTimes(1);
        expect(component.dialogService.closeProgressSpinnerDialog).toHaveBeenCalledTimes(1);
        expect(component.getAll).toHaveBeenCalled();

      })));

      it('should test delete item method passing an object that have an cpfCnpj',(fakeAsync(() =>{
        let objectWithAnIdentification = {cpfCnpj: 1};
        spyOn(component,'getAll');
        component.deleteItem(objectWithAnIdentification);
        tick(50)
        expect(component.service.delete).toHaveBeenCalledTimes(1);
        expect(component.dialogService.openProgressDialog).toHaveBeenCalledTimes(1);
        expect(component.dialogService.closeProgressSpinnerDialog).toHaveBeenCalledTimes(1);
        expect(component.getAll).toHaveBeenCalled();
      })));

      it('should call the service getAll inside component getAll',() => {
        component.getAll();
        expect(component.service.getAll).toHaveBeenCalled();
      });

      it('should call the service getAllByCustomerId inside method component get all',(fakeAsync(() =>{
        component.customerId = '1';
        spyOn(component,'afterReturnListObserver');
        component.getAll();
        expect(component.service.getAllByCustomerId).toHaveBeenCalled();
        tick(50)
        expect(component.afterReturnListObserver).toHaveBeenCalled();
      })));


      it('should get an observer from afterReturnListObserver method',() =>{
        let observer = component.afterReturnListObserver();
        expect(observer.next).toBeTruthy();
        expect(observer.error).toBeTruthy();
      });

      it('should test the observer next method of afterReturnListObserver method when there is no mapper',() =>{
        spyOn(component,'updateDataSource')
        let observer = component.afterReturnListObserver();
        observer.next([{id:1,nome:'cliente1'},{id:2,nome:'cliente2'}]);
        expect(component.tableData).toEqual([{id:1,nome:'cliente1'},{id:2,nome:'cliente2'}]);
        expect(component.updateDataSource).toHaveBeenCalled();

      });

      it('should test the observer next method of afterReturnListObserver method when there is a mapper',() =>{
        

        let observer = component.afterReturnListObserver();
        let list = [{id:1,nome:'cliente1'},{id:2,nome:'cliente2'}];
        let maper:Mapper = {mapItens:([])=>{ return []}}
   
        component.mapper = maper;

        spyOn(component,'updateDataSource')
        spyOn(component.mapper,'mapItens').and.returnValue([{id:1,nome:'CLIENTE1'},{id:2,nome:'CLIENTE2'}]);
      
        
        observer.next(list);
        
        
        expect(component.mapper).toBeDefined();
        expect(component.tableData).toHaveSize(2);
        expect(component.updateDataSource).toHaveBeenCalled();
        expect(component.mapper.mapItens).toHaveBeenCalled();

      });

      it('should run error method of observer',() => {
        let observer = component.afterReturnListObserver();
        observer.error({message:'this is an error'});


      })

      it('should test the openDialog method that return a true false observable',(fakeAsync(() =>{
        
        
        let obj = {};
        spyOn(component,'deleteItem');
        component.openDialog(obj);
        expect(component.dialogService.openConfirmationDialog).toHaveBeenCalled();
        tick(100);
        expect(component.deleteItem).toHaveBeenCalled();

      })));

      it('should test the openDialog method that return a true false observable',(fakeAsync(() =>{
        
        /**
         * Alterando resultado do observable que retorna do metodo.
         */
        let openConfirmationMethodFalse = jasmine.createSpy('confirmationFalse').and.returnValues(of(false));
        component.dialogService.openConfirmationDialog = openConfirmationMethodFalse;

        let obj = {};
        spyOn(component,'deleteItem');
        component.openDialog(obj);
        expect(component.dialogService.openConfirmationDialog).toHaveBeenCalled();
        tick(100);
        expect(component.deleteItem).not.toHaveBeenCalled();

      })));


      it('should test sendObjectToEdit emit object',() => {
          spyOn(component.editObjectEmitter,'emit');
          component.sendObjectToEdit({});
          expect(component.editObjectEmitter.emit).toHaveBeenCalled();
      })







})