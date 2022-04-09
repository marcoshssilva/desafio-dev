package br.com.marcoshssilva.desafiodev.entities.enums;

public enum TipoTransacao {
	
	DEBITO(1, NaturezaTipoDeTransacao.ENTRADA),
	BOLETO(2, NaturezaTipoDeTransacao.SAIDA),
	FINANCIAMENTO(3, NaturezaTipoDeTransacao.SAIDA),
	CREDITO(4, NaturezaTipoDeTransacao.ENTRADA),
	RECEBIMENTO_EMPRESTIMO(5, NaturezaTipoDeTransacao.ENTRADA),
	VENDAS(6, NaturezaTipoDeTransacao.ENTRADA),
	RECEBIMENTO_TED(7, NaturezaTipoDeTransacao.ENTRADA),
	RECEBIMENTO_DOC(8, NaturezaTipoDeTransacao.ENTRADA),
	ALUGUEL(9, NaturezaTipoDeTransacao.SAIDA);
	
	private final int cod;
	private final NaturezaTipoDeTransacao naturezaTipo;
	
	TipoTransacao(int cod, NaturezaTipoDeTransacao naturezaTipo){
		this.cod = cod;
		this.naturezaTipo = naturezaTipo;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public NaturezaTipoDeTransacao getNaturezaTipoDeTransacao() {
		return this.naturezaTipo;
	}
	
}
