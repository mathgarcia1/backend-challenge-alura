package br.com.alura.backendchallenge.dto;

import br.com.alura.backendchallenge.model.Receitas;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Getter
public class ReceitasDto {
    public String descricao;
    public Double valor;
    public LocalDate data;

    public ReceitasDto(Receitas receitas){
        this.descricao = receitas.getDescricao();
        this.valor = receitas.getValor();
        this.data = receitas.getData();
    }

    public static Page<ReceitasDto> converter(Page<Receitas> receitas){
        return receitas.map(ReceitasDto::new);
    }
}
