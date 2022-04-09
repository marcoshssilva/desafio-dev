package br.com.marcoshssilva.desafiodev.services;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import br.com.marcoshssilva.desafiodev.models.LojaModelDto;
import br.com.marcoshssilva.desafiodev.models.TransacaoModelForLojaModelDto;
import br.com.marcoshssilva.desafiodev.repositories.TransacaoRepository;
import br.com.marcoshssilva.desafiodev.services.exceptions.NoIdEntityException;
import br.com.marcoshssilva.desafiodev.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public TransacaoService() {
        super();
    }

    public TransacaoService(TransacaoRepository transacaoRepository) {
        super();
        this.transacaoRepository = transacaoRepository;
    }

    public List<Transacao> getAll() {
        return this.transacaoRepository.findAll();
    }

    public Transacao getById(Integer id) {
        return this.transacaoRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Transacao save(Transacao transacao) {
        return this.transacaoRepository.save(transacao);
    }

    public void delete(Transacao transacao) {
        // caso objeto não possua ID ou seja nulo
        if (transacao == null || transacao.getId() == null) throw new NoIdEntityException();
        // caso objeto não exista no banco
        if (!this.transacaoRepository.existsById(transacao.getId())) throw new NotFoundException();
        // apagando no banco de dados
        this.transacaoRepository.delete(transacao);
    }

    public List<LojaModelDto> getAllResumedByLoja() {
        List<String> lojasString = transacaoRepository.findNomesLoja();
        List<LojaModelDto>  lojasModel = lojasString.stream().map(loja -> new LojaModelDto(loja)).toList();

        lojasModel.forEach(loja -> {
            List<Transacao> transacaos = transacaoRepository.findAllByNomeLoja(loja.getNomeLoja());
            loja.setTransacoes(
                    transacaos
                            .stream()
                            .map(transacao -> TransacaoModelForLojaModelDto.fromTransacao(transacao))
                            .toList()
            );
        });

        return lojasModel;
    }

}
