package br.com.alura.backendchallenge.form;

import br.com.alura.backendchallenge.model.Despesas;
import br.com.alura.backendchallenge.repository.DespesasRepository;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AtualizacaoDespesasForm {
    private String descricao;
    private Double valor;
    private LocalDate data;

    public Despesas atualizar(Long id, DespesasRepository despesasRepository){
        Despesas despesas = despesasRepository.getOne(id);
        despesas.setDescricao(this.descricao);
        despesas.setValor(this.valor);
        despesas.setData(this.data);
        return despesas;
    }
}
