package br.com.rinhadebackend.rinha_de_backend.pessoa;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pessoa")
public class PessoaModel {
  @JsonView(PessoaViews.RetornoDetalhePessoa.class)
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @JsonView(PessoaViews.RetornoCriacaoPessoa.class)
  @NotNull(message = "Apelido não pode ser nulo")
  @Column(columnDefinition = "VARCHAR(32)", unique = true, nullable = false)
  private String apelido;

  @JsonView(PessoaViews.RetornoCriacaoPessoa.class)
  @Pattern(regexp = "^[^\\d]+$", message = "Nome deve ser string e não número")
  @NotNull(message = "Nome não pode ser nulo")
  @Column(columnDefinition = "VARCHAR(100)", nullable = false)
  private String nome;

  @JsonView(PessoaViews.RetornoCriacaoPessoa.class)
  @NotNull(message = "Data de nascimento não pode ser nula")
  @Column(columnDefinition = "VARCHAR(10)", nullable = false)
  private String nascimento;

  @JsonView(PessoaViews.RetornoCriacaoPessoa.class)
  @ElementCollection
  @CollectionTable(name = "stack", joinColumns = @JoinColumn(name = "id"))
  @Column(name = "valor", columnDefinition = "VARCHAR(32)", nullable = false)
  private List<@Pattern(regexp = "^[^\\d]+$", message = "Stack deve ser string e não número") @NotNull(message = "Stack não pode ser nulo") String> stack;

  public List<String> getStack() {
    if (this.stack != null && this.stack.size() <= 0)
      this.stack = null;
    return this.stack;
  }

}
