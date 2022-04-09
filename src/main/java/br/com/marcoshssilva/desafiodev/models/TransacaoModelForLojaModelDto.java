package br.com.marcoshssilva.desafiodev.models;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import br.com.marcoshssilva.desafiodev.entities.enums.NaturezaTipoDeTransacao;
import br.com.marcoshssilva.desafiodev.entities.enums.TipoTransacao;

import java.time.LocalDateTime;

public class TransacaoModelForLojaModelDto {

    private LocalDateTime data;
    private String cpf;
    private String cartao;
    private Double valor;
    private TipoTransacao tipo;

    public TransacaoModelForLojaModelDto() {
        super();
    }

    public TransacaoModelForLojaModelDto(LocalDateTime data, String cpf, String cartao, Double valor, TipoTransacao tipo) {
        this.data = data;
        this.cpf = cpf;
        this.cartao = cartao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public static TransacaoModelForLojaModelDto fromTransacao(final Transacao transacao) {
        // Infelizmente, o ObjectMapper deu problema por conta do LocalDateTime
        TransacaoModelForLojaModelDto model = new TransacaoModelForLojaModelDto(
                transacao.getData(),
                transacao.getCpf(),
                transacao.getCartao(),
                transacao.getValor(),
                transacao.getTipo()
        );

        return model;
    }

    public String getSinal() {
        return this.tipo.getNaturezaTipoDeTransacao().getSinal();
    }

    public NaturezaTipoDeTransacao getNatureza(){
        return this.tipo.getNaturezaTipoDeTransacao();
    }

    // getters e setters padrões

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

}
