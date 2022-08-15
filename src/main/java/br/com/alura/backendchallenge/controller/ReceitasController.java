package br.com.alura.backendchallenge.controller;

import br.com.alura.backendchallenge.dto.DetalhesReceitasDto;
import br.com.alura.backendchallenge.dto.ReceitasDto;
import br.com.alura.backendchallenge.form.AtualizacaoReceitasForm;
import br.com.alura.backendchallenge.model.Receitas;
import br.com.alura.backendchallenge.repository.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {

    @Autowired
    ReceitasRepository receitasRepository;

    @GetMapping
    public Page<ReceitasDto> lista(@RequestParam(required = false)String descricao,
                                             @PageableDefault(sort = "id", direction = Sort.Direction.ASC )Pageable paginacao){
        Page<Receitas> receitas;
        if(descricao == null || descricao.isEmpty()){
            receitas = receitasRepository.findAll(paginacao);
        }else{
            receitas = receitasRepository.findByDescricao(descricao, paginacao);
        }
        return ReceitasDto.converter(receitas);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Receitas receitas, UriComponentsBuilder uriComponentsBuilder){
//        if(!receitasRepository.existsByDescricaoEMes(receitas.getDescricao(), LocalDate.from(receitas.getData().getMonth().))){
//            receitasRepository.save(receitas);
//            URI uri = uriComponentsBuilder.path("receitas/{id}").buildAndExpand(receitas.getId()).toUri();
//            return ResponseEntity.created(uri).body(new Receitas());
//        }
        if(!receitasRepository.existsByDescricao(receitas.getDescricao())){
            receitasRepository.save(receitas);
            URI uri = uriComponentsBuilder.path("receitas/{id}").buildAndExpand(receitas.getId()).toUri();
            return ResponseEntity.created(uri).body(new Receitas());
        }
        return ResponseEntity.status(HttpStatus.FOUND).body("Essa receita já existe");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesReceitasDto> detalhar(@PathVariable Long id){
        Optional<Receitas> receitas = receitasRepository.findById(id);
        if(receitas.isPresent()){
            return ResponseEntity.ok(new DetalhesReceitasDto(receitas.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Receitas> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoReceitasForm form){
        Optional<Receitas> optional = receitasRepository.findById(id);
        if(optional.isPresent()){
            Receitas receitas = form.atualizar(id, receitasRepository);
            return ResponseEntity.ok(new Receitas());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Receitas> deletar(@PathVariable Long id){
        Optional<Receitas> optional = receitasRepository.findById(id);
        if(optional.isPresent()){
            receitasRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
