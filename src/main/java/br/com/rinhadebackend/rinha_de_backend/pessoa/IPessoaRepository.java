package br.com.rinhadebackend.rinha_de_backend.pessoa;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPessoaRepository extends JpaRepository<PessoaModel, UUID> {
}