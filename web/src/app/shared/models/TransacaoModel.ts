export interface TransacaoModel {
  data: string
  cpf: string
  cartao: string
  valor: number
  tipo: 'DEBITO' | 'BOLETO' | 'FINANCIAMENTO' | 'CREDITO' | 'RECEBIMENTO_EMPRESTIMO' | 'VENDAS' | 'RECEBIMENTO_TED' | 'RECEBIMENTO_DOC' | 'ALUGUEL'
  natureza: 'ENTRADA' | 'SAIDA'
  sinal: '+' | '-'
}
