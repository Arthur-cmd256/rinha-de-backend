package br.com.rinhadebackend.rinha_de_backend.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPessoaRepository extends JpaRepository<PessoaModel, Long> {
}