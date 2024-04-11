package br.com.rinhadebackend.rinha_de_backend.pessoa;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPessoaRepository extends JpaRepository<PessoaModel, UUID> {

  @Query("SELECT a FROM PessoaModel a WHERE a.apelido=:apelido")
  PessoaModel buscaPorApelido(String apelido);

  // PessoaModel findByApelido(String apelido);

}