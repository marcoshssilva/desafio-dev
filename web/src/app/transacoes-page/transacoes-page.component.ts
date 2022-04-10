import { Component, OnInit } from '@angular/core';
import { LojaModel } from '../shared/models/LojaModel';
import { TransacaoService } from '../shared/services/transacao.service';

@Component({
  selector: 'app-transacoes-page',
  templateUrl: './transacoes-page.component.html',
  styleUrls: ['./transacoes-page.component.css']
})
export class TransacoesPageComponent implements OnInit {

  private lojas : LojaModel[] = []

  constructor(
    private transacaoService : TransacaoService
  ) {}

  ngOnInit(): void {
    this.transacaoService.getTransacoesResumedByLoja().subscribe(data => {
      this.lojas = data
    })
  }

  get Lojas() : LojaModel[] {
    return this.lojas
  }

}
