import { Component } from '@angular/core';
import { TransacaoService } from './shared/services/transacao.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'desafiodev-frontend';
  constructor(
    private transacaoService : TransacaoService
  ) {}

  get Lojas$() {
    return this.transacaoService.getTransacoesResumedByLoja();
  }
}
