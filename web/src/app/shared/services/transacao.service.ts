import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LojaModel } from '../models/LojaModel';

@Injectable({
  providedIn: 'root',
})
export class TransacaoService {
  constructor(private http: HttpClient) {}

  public getTransacoesResumedByLoja(): Observable<LojaModel[]> {
    return this.http.get<LojaModel[]>(
      `${environment.api_host}/transacoes/lojas`,
      { responseType: 'json', observe: 'body' }
    );
  }

  public uploadFile(fileUpload: File) {
    const formData = new FormData();
    formData.append('file', fileUpload);

    return this.http.post(
      `${environment.api_host}/transacoes/upload`,
      formData,
      {
        observe: 'response',
        responseType: 'json',
      }
    );
  }
}
