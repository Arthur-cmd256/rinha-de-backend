package br.com.rinhadebackend.rinha_de_backend.pessoa;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

  @Column(columnDefinition = "VARCHAR(32)")
  private String apelido;

  @Column(columnDefinition = "VARCHAR(100)")
  private String nome;

  @Column(columnDefinition = "VARCHAR(10)")
  private String nascimento;

  @ElementCollection
  @CollectionTable(name = "stack", joinColumns = @JoinColumn(name = "id"))
  @Column(name = "valor", length = 32, nullable = false)
  private List<String> stack;

}
