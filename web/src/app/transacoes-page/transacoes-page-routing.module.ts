import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LojasPageComponent } from './components/lojas-page/lojas-page.component';
import { UploadPageComponent } from './components/upload-page/upload-page.component';
import { TransacoesPageComponent } from './transacoes-page.component';

const routes: Routes = [
  {
    path: '',
    component: TransacoesPageComponent,
    children: [
      {
        path: 'lojas',
        component: LojasPageComponent
      },
      {
        path: 'upload',
        component: UploadPageComponent
      },
      {
        path: '',
        redirectTo: 'lojas'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransacoesPageRoutingModule { }
