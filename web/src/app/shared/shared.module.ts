import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { TransacaoService } from './services/transacao.service';
import { CpfPipe } from './pipes/cpf.pipe';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
  ],
  providers: [
    TransacaoService,
    CpfPipe
  ]
})
export class SharedModule { }
