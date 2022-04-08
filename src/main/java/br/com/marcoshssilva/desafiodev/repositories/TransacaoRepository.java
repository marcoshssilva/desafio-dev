package br.com.marcoshssilva.desafiodev.repositories;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

}
