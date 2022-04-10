import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'transacoes',
    loadChildren: () =>
      import('./transacoes-page/transacoes-page.module').then(
        (m) => m.TransacoesPageModule
      ),
  },
  {
    path: '',
    redirectTo: 'transacoes',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
