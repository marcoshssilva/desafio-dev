import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransacoesPageRoutingModule } from './transacoes-page-routing.module';
import { TransacoesPageComponent } from './transacoes-page.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    TransacoesPageComponent
  ],
  imports: [
    CommonModule,
    TransacoesPageRoutingModule,
    SharedModule
  ]
})
export class TransacoesPageModule { }
