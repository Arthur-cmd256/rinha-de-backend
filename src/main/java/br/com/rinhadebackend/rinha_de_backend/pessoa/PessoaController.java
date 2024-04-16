package br.com.rinhadebackend.rinha_de_backend.pessoa;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

  @Autowired
  private IPessoaRepository repository;

  @PostMapping()
  public ResponseEntity<?> criaPessoa(@Valid @RequestBody PessoaModel pessoa, BindingResult result) {
    try {
      if (!result.hasErrors()) {

        if (repository.buscaPorApelido(pessoa.getApelido()) != null) {
          throw new DataIntegrityViolationException("Apelido já existente");
        }

        var novaPessoa = new PessoaModel(
            null,
            pessoa.getApelido(),
            pessoa.getNome(),
            pessoa.getNascimento(),
            pessoa.getStack());
        return ResponseEntity.ok(repository.save(novaPessoa));
      }
      for (FieldError fieldError : result.getFieldErrors()) {
        switch (fieldError.getCode()) {
          case "NotNull":
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(fieldError.getDefaultMessage());
          case "Pattern":
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldError.getDefaultMessage());
          default:
            break;
        }
      }
    } catch (DataIntegrityViolationException err) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err.getMessage());
    }
    return ResponseEntity.internalServerError().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getDetalhesPessoa(@PathVariable UUID id) {

    try {
      PessoaModel pessoa = repository.findById(id).orElse(null);
      if (pessoa == null)
        throw new NullPointerException();

      return ResponseEntity.ok(pessoa);
    } catch (NullPointerException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("")
  public String getMethodName(@RequestParam String t) {
    return new String();
  }

}
