package br.com.marcoshssilva.desafiodev.services;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import br.com.marcoshssilva.desafiodev.entities.enums.TipoTransacao;
import br.com.marcoshssilva.desafiodev.repositories.TransacaoRepository;

import br.com.marcoshssilva.desafiodev.services.exceptions.NoIdEntityException;
import br.com.marcoshssilva.desafiodev.services.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class TransacaoServiceTests {

    @Autowired
    private TransacaoRepository transacaoRepository;
    private TransacaoService transacaoService;
    private Transacao mockTransacao;

    @Test
    void shouldDeleteTransacao() {
        Assertions.assertDoesNotThrow(() -> this.transacaoService.delete(mockTransacao));
    }

    @Test
    void shouldReturnAllTransacoesFromDatabase(){
        int quantidadeObjetosSalvos = 3;
        List<Transacao> mockedTransacoes = this.transacaoService.getAll();
        Assertions.assertEquals(quantidadeObjetosSalvos, mockedTransacoes.size());
    }

    @Test
    void shouldReturnTransacaoByCod() {
        Integer cod = 1;
        Transacao t = Assertions.assertDoesNotThrow( () -> this.transacaoService.getById(cod));
        Assertions.assertEquals(cod, t.getId());
    }

    @Test
    void shouldReturnNotFoundExceptionCaseDontExists() {
        Integer cod = 10000;
        Assertions.assertThrows(NotFoundException.class, () -> this.transacaoService.getById(cod));
    }

    @Test
    void shouldPrintErrorOnDeleteWithoutNullOrNoID() {
        final Transacao tr1 = null;
        Assertions.assertThrows(NoIdEntityException.class, () -> this.transacaoService.delete(tr1));
        final Transacao tr2 = new Transacao();
        tr2.setId(null);
        Assertions.assertThrows(NoIdEntityException.class, () -> this.transacaoService.delete(tr2));
    }

    @BeforeEach
    void setup() {
        Transacao t1 = new Transacao();

        t1.setCartao("2344****1222");
        t1.setCpf("84515254073");
        t1.setData(LocalDateTime.of(2019, 3, 1, 12, 32,22));
        t1.setDonoLoja("MARCOS PEREIRA");
        t1.setNomeLoja("MERCADO DA AVENIDA");
        t1.setValor(2.00);
        t1.setTipo(TipoTransacao.RECEBIMENTO_DOC);

        Transacao t2 = new Transacao();

        t2.setCartao("7677****8778");
        t2.setCpf("23270298056");
        t2.setData(LocalDateTime.of(2019, 3, 1, 14, 18,8));
        t2.setDonoLoja("JOSÉ COSTA");
        t2.setNomeLoja("MERCEARIA 3 IRMÃOS");
        t2.setValor(5.00);
        t2.setTipo(TipoTransacao.BOLETO);

        Transacao t3 = new Transacao();

        t3.setCartao("6777****1313");
        t3.setCpf("84515254073");
        t3.setData(LocalDateTime.of(2019, 3, 1, 17, 27,12));
        t3.setDonoLoja("MARCOS PEREIRA");
        t3.setNomeLoja("MERCADO DA AVENIDA");
        t3.setValor(192.00);
        t3.setTipo(TipoTransacao.FINANCIAMENTO);

        this.transacaoService = new TransacaoService(transacaoRepository);
        this.mockTransacao =  this.transacaoRepository.save(t1);

        this.transacaoRepository.save(t2);
        this.transacaoRepository.save(t3);
    }

}
