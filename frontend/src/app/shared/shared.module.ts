
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfirmationDialogComponent } from './dialogs/confirmation-dialog/confirmation-dialog.component';
import { CrudMenuComponent } from './crud-menu/crud-menu.component';
import { ErrorDialogComponent } from './dialogs/error-dialog/error-dialog.component';
import { ItensTableComponent } from './itens-table/itens-table.component';
import { ProgressComponent } from './dialogs/progress/progress.component';
import { SuccessDialogComponent } from './dialogs/success-dialog/success-dialog.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { CpfCnpjPipePipe } from './pipes/cpf-cnpj-pipe.pipe';
import { PhonePipe } from './pipes/phone.pipe';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModuleModule } from './material-module.module';
import { DialogWindowHeaderComponent } from './dialog-window-header/dialog-window-header.component';
import { SvgListIconComponent } from './svg-list-icon/svg-list-icon.component';
import { DaysChipComponent } from './days-chip/days-chip.component';
import { SvgSaveIconComponent } from './svg-save-icon/svg-save-icon.component';
import { SvgAddIconComponent } from './svg-add-icon/svg-add-icon.component';





@NgModule({
  declarations: [
    ConfirmationDialogComponent,
    CrudMenuComponent,
    ErrorDialogComponent,
    ItensTableComponent,
    ProgressComponent,
    SuccessDialogComponent,
    CpfCnpjPipePipe,
    PhonePipe,
    DialogWindowHeaderComponent,
    SvgListIconComponent,
    DaysChipComponent,
    SvgSaveIconComponent,
    SvgAddIconComponent,

  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialModuleModule,
    ReactiveFormsModule


  ],
  exports:[
    ConfirmationDialogComponent,
    CrudMenuComponent,
    ErrorDialogComponent,
    ItensTableComponent,
    ProgressComponent,
    SuccessDialogComponent,
    PhonePipe,
    CpfCnpjPipePipe,
    DialogWindowHeaderComponent,
    SvgListIconComponent,
    ReactiveFormsModule,
    DaysChipComponent,
    SvgSaveIconComponent,
    SvgAddIconComponent,

  ]
})
export class SharedModule { }
