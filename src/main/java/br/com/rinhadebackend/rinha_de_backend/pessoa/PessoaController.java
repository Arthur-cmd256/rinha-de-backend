package br.com.rinhadebackend.rinha_de_backend.pessoa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

  @GetMapping("/teste")
  public PessoaModel getPessoa() {
    return new PessoaModel("Arthur", "22");
  }

}
