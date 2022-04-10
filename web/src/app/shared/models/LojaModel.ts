import { TransacaoModel } from "./TransacaoModel"

export interface LojaModel {
  nomeLoja: string
  saldo: number
  transacoes: TransacaoModel[]
}
