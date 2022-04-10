import { Component, OnInit } from '@angular/core';
import { LojaModel } from 'src/app/shared/models/LojaModel';
import { TransacaoService } from 'src/app/shared/services/transacao.service';

@Component({
  selector: 'app-lojas-page',
  templateUrl: './lojas-page.component.html',
  styleUrls: ['./lojas-page.component.css']
})
export class LojasPageComponent implements OnInit {

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
