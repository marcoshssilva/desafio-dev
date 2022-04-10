import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { TransacaoService } from './services/transacao.service';
import { CpfPipe } from './pipes/cpf.pipe';


@NgModule({
  declarations: [
    CpfPipe
  ],
  imports: [
    CommonModule,
    HttpClientModule,
  ],
  providers: [
    TransacaoService
  ],
  exports: [
    CpfPipe
  ]
})
export class SharedModule { }
