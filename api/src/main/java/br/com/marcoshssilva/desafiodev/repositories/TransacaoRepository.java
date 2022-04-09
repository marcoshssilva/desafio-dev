package br.com.marcoshssilva.desafiodev.repositories;

import br.com.marcoshssilva.desafiodev.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    @Query("SELECT DISTINCT obj.nomeLoja FROM Transacao obj")
    public List<String> findNomesLoja();

    public List<Transacao> findAllByNomeLoja(String nomeLoja);

}
