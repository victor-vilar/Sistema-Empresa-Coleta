import { TestBed } from "@angular/core/testing";
import { DialogServiceService } from "./dialog-service.service"
import { SharedModule } from "../shared.module";
import { MatSnackBar } from "@angular/material/snack-bar";
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { Router, ActivatedRoute } from "@angular/router";
import { CrudMenuComponent } from "../crud-menu/crud-menu.component";
import { ConfirmationDialogComponent } from "../dialogs/confirmation-dialog/confirmation-dialog.component";
import { of } from "rxjs";



describe('DialogServiceService',() => {

    let service: DialogServiceService;
    let dialog:jasmine.SpyObj<MatDialog>;
    let router:jasmine.SpyObj<Router>;
    let activeRoute:jasmine.SpyObj<ActivatedRoute>;
    let snackBar:jasmine.SpyObj<MatSnackBar>;
    let afterCloseSpy:jasmine.SpyObj<any>;

    beforeEach(async() =>{

        await TestBed.configureTestingModule({
            declarations:[CrudMenuComponent],
            imports:[SharedModule],
            providers:[
                DialogServiceService,
                
                {
                    provide:MatDialog,
                    useValue: jasmine.createSpyObj('MatDialog',{'open':{afterClosed: () => of(true)}})
                },
                {
                    provide:Router,
                    useValue: jasmine.createSpyObj('Router',[''])
                },
                {
                    provide:ActivatedRoute,
                    useValue: jasmine.createSpyObj('ActivatedRoute',[''])
                },
                {
                    provide:MatSnackBar,
                    useValue: jasmine.createSpyObj('MatSnackBar',['open'])
                }
            ]

        });

        service = TestBed.inject(DialogServiceService);
        dialog = TestBed.inject(MatDialog) as jasmine.SpyObj<MatDialog>;
        router = TestBed.inject(Router) as jasmine.SpyObj<Router>
        activeRoute = TestBed.inject(ActivatedRoute) as jasmine.SpyObj<ActivatedRoute>
        snackBar = TestBed.inject(MatSnackBar) as jasmine.SpyObj<MatSnackBar>
        
        afterCloseSpy = spyOn<any>(service,'afterCloseDialog');
    });

    it('Should test method to create a snack bar from angular NOT passing snackbar position',() => {
        
        service.openSnackBar('this is a test','test');
        expect(snackBar.open).toHaveBeenCalled();
        

    });

    it('Should test method to create a snack bar from angular passing snackbar position',() => {
        
        service.openSnackBar('this is a test','test','center','bottom');
        expect(snackBar.open).toHaveBeenCalled();
        
    });

    it('Should test the open dialog method successfully', () => {
        
        service.openDialog(ConfirmationDialogComponent,{},'/')
        expect(dialog.open).toHaveBeenCalled();
        expect(afterCloseSpy).toHaveBeenCalled();

    })

    it('Should test the open dialog passing customer id method successfully', () => {
        
        service.openDialogPassingCustomerId(ConfirmationDialogComponent,{},'1','/');
        expect(dialog.open).toHaveBeenCalled();
        expect(afterCloseSpy).toHaveBeenCalled();

    })

    it('Should test the open dialog passing customer id return afterClose observable method successfully', () => {
        
        
         let obs = service.openDialogPassingCustomerIdAndReturnCloseObservable(ConfirmationDialogComponent,{},'1');
         expect(dialog.open).toHaveBeenCalled();
         expect(obs).not.toBeNull();

    })

    it('should test the openErrorDialog method', () => {

        service.openErrorDialog('this is a test');
        expect(dialog.open).toHaveBeenCalled();

    })

    it('should test the openConfirmationDialog dialog method', () => {

        let $obs = service.openConfirmationDialog();
        expect(dialog.open).toHaveBeenCalled();
        expect($obs).not.toBeNull();

    })

    it('should test the openConfirmCloseDialog  method', () => {

        let $obs = service.openConfirmCloseDialog('this is an test');
        expect(dialog.open).toHaveBeenCalled();
        expect($obs).not.toBeNull();

    })

    it('should test the openSuccessDialog  method', () => {

        
        service.openSucessDialog('this is an test','/');
        expect(dialog.open).toHaveBeenCalled();
        expect(afterCloseSpy).toHaveBeenCalled();

    })


    it('should test the openSuccessDialogWithoutRedirect  method', () => {

        
        service.openSuccessDialogWithoutRedirect('this is an test');
        expect(dialog.open).toHaveBeenCalled();
        
    })

    it('should test method open ProgressDialog', () => {

        service.openProgressDialog();
        expect(dialog.open).toHaveBeenCalled();

    })



})