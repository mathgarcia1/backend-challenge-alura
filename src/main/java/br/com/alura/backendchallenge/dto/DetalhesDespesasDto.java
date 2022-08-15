package br.com.alura.backendchallenge.dto;

import br.com.alura.backendchallenge.model.Despesas;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Getter
public class DetalhesDespesasDto {
    private String descricao;
    private Double valor;
    private LocalDate data;

    public DetalhesDespesasDto(Despesas despesas){
        this.descricao = despesas.getDescricao();
        this.valor = despesas.getValor();
        this.data = despesas.getData();
    }

    public Page<DetalhesDespesasDto> converter(Page<Despesas> despesas){
        return despesas.map(DetalhesDespesasDto::new);
    }
}
