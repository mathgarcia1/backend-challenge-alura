package br.com.alura.backendchallenge.form;

import br.com.alura.backendchallenge.model.Receitas;
import br.com.alura.backendchallenge.repository.ReceitasRepository;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AtualizacaoReceitasForm {
    private String descricao;
    private Double valor;
    private LocalDate data;

    public Receitas atualizar(Long id, ReceitasRepository receitasRepository){
        Receitas receitas = receitasRepository.getOne(id);
        receitas.setDescricao(this.descricao);
        receitas.setValor(this.valor);
        receitas.setData(this.data);
        return receitas;
    }
}
