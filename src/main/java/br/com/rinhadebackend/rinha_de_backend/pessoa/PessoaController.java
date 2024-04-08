package br.com.rinhadebackend.rinha_de_backend.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

  @Autowired
  private IPessoaRepository repository;

  @PostMapping()
  public PessoaModel criaPessoa(@Valid @RequestBody PessoaModel pessoa) {
    var novaPessoa = new PessoaModel(
        null,
        pessoa.getApelido(),
        pessoa.getNome(),
        pessoa.getNascimento(),
        pessoa.getStack());
    return repository.save(novaPessoa);
  }

}
