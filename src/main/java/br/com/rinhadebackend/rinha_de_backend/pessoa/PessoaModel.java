package br.com.rinhadebackend.rinha_de_backend.pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Pessoa")
public class PessoaModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence_id_pessoas")
  @SequenceGenerator(name = "sequence_id_pessoas", sequenceName = "sequence_pessoa", allocationSize = 1)
  private Long id;

  private String name;
  private String year;
}
