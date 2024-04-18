package br.com.rinhadebackend.rinha_de_backend.pessoa;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPessoaRepository extends JpaRepository<PessoaModel, UUID> {

  @Query("SELECT a FROM PessoaModel a WHERE a.apelido=:apelido")
  PessoaModel buscaPorApelido(String apelido);

  // PessoaModel findByApelido(String apelido);
  @Query(value = """
      select p.*, array_agg(s.valor) from pessoa p
      left join stack s
        on s.id = p.id
      where
        upper(p.apelido) like upper('%'||:termo||'%') or
        upper(p.nome) like upper('%'||:termo||'%') or
        upper(s.valor) like upper('%'||:termo||'%')
      group by p.id
      limit 50;
      """, nativeQuery = true)
  List<PessoaModel> buscaPorTermo(String termo);

}