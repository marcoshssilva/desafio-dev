import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransacoesPageRoutingModule } from './transacoes-page-routing.module';
import { TransacoesPageComponent } from './transacoes-page.component';
import { SharedModule } from '../shared/shared.module';
import { UploadPageComponent } from './components/upload-page/upload-page.component';
import { LojasPageComponent } from './components/lojas-page/lojas-page.component';


@NgModule({
  declarations: [
    TransacoesPageComponent,
    UploadPageComponent,
    LojasPageComponent
  ],
  imports: [
    CommonModule,
    TransacoesPageRoutingModule,
    SharedModule
  ]
})
export class TransacoesPageModule { }
