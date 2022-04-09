package br.com.marcoshssilva.desafiodev.services;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import br.com.marcoshssilva.desafiodev.repositories.TransacaoRepository;
import br.com.marcoshssilva.desafiodev.services.exceptions.NoIdEntityException;
import br.com.marcoshssilva.desafiodev.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
