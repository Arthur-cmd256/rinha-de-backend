package br.com.rinhadebackend.rinha_de_backend.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

  @Autowired
  private IPessoaRepository repository;

  @PostMapping("/cria")
  public PessoaModel criaPessoa(@RequestBody PessoaModel pessoa) {
    var novaPessoa = new PessoaModel(null, pessoa.getName(), pessoa.getYear());
    return repository.save(novaPessoa);
  }

}
