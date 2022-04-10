import { Component, OnInit } from '@angular/core';
import { LojaModel } from './shared/models/LojaModel';
import { TransacaoService } from './shared/services/transacao.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  title = 'desafiodev-frontend';
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
