package br.com.alura.backendchallenge.controller;

import br.com.alura.backendchallenge.dto.DespesasDto;
import br.com.alura.backendchallenge.dto.DetalhesDespesasDto;
import br.com.alura.backendchallenge.form.AtualizacaoDespesasForm;
import br.com.alura.backendchallenge.model.Despesas;
import br.com.alura.backendchallenge.repository.DespesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

    @Autowired
    DespesasRepository despesasRepository;

    @GetMapping
    public Page<DespesasDto> lista(@RequestParam(required = false) String descricao,
                                   @PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable paginacao){
        Page<Despesas> despesas;
        if(descricao == null || descricao.isEmpty()){
            despesas = despesasRepository.findAll(paginacao);
            return DespesasDto.converter(despesas);
        }else{
            despesas = despesasRepository.findByDescricao(descricao, paginacao);
            return DespesasDto.converter(despesas);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Despesas> cadastrar(@RequestBody @Valid Despesas despesas, UriComponentsBuilder uriComponentsBuilder){
        despesasRepository.save(despesas);
        URI uri = uriComponentsBuilder.path("/despesas/{id}").buildAndExpand(despesas.getId()).toUri();
        return ResponseEntity.created(uri).body(new Despesas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDespesasDto> detalhar(@PathVariable Long id){
        Optional<Despesas> despesas = despesasRepository.findById(id);
        if(despesas.isPresent()){
            return ResponseEntity.ok(new DetalhesDespesasDto(despesas.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Despesas> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoDespesasForm form){
        Optional<Despesas> optional = despesasRepository.findById(id);
        if(optional.isPresent()){
            Despesas despesas = form.atualizar(id, despesasRepository);
            return ResponseEntity.ok(new Despesas());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Despesas> deletar(@PathVariable Long id){
        Optional<Despesas> optional = despesasRepository.findById(id);
        if(optional.isPresent()){
            despesasRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
