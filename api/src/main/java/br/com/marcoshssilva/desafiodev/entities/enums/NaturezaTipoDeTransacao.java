package br.com.marcoshssilva.desafiodev.entities.enums;

public enum NaturezaTipoDeTransacao {
	
    ENTRADA(0, "+"), 
    SAIDA(1, "-");

    private final Integer id;
    private final String sinal;
    
    NaturezaTipoDeTransacao(Integer id, String sinal) {
        this.id = id;
        this.sinal = sinal;
    }

    public Integer getId() {
        return id;
    }

    public String getSinal() {
        return sinal;
    }
}
