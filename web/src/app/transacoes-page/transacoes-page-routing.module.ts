import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TransacoesPageComponent } from './transacoes-page.component';

const routes: Routes = [
  {
    path: '',
    component: TransacoesPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransacoesPageRoutingModule { }
