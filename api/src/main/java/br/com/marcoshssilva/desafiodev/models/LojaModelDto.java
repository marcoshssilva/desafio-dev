package br.com.marcoshssilva.desafiodev.models;

import java.util.LinkedList;
import java.util.List;

public class LojaModelDto {

    private String nomeLoja;
    private List<TransacaoModelForLojaModelDto> transacoes = new LinkedList<>();
    private Double saldo;

    public LojaModelDto() {
        super();
    }

    public LojaModelDto(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public LojaModelDto(String nomeLoja, List<TransacaoModelForLojaModelDto> transacoes) {
        this.nomeLoja = nomeLoja;
        this.transacoes = transacoes;
    }

    public Double getSaldo() {
        this.saldo = 0.00;
        transacoes.forEach(transacao -> {
            switch (transacao.getNatureza()) {
                case ENTRADA: this.saldo += transacao.getValor();
                    break;
                case SAIDA:   this.saldo -= transacao.getValor();
                    break;
            }
        });
        return this.saldo;
    }

    // getters e setters

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public List<TransacaoModelForLojaModelDto> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<TransacaoModelForLojaModelDto> transacoes) {
        this.transacoes = transacoes;
    }

}
