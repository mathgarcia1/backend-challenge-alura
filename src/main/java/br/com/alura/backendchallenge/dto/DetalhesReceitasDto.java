package br.com.alura.backendchallenge.dto;

import br.com.alura.backendchallenge.model.Receitas;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Getter
public class DetalhesReceitasDto {
    private String descricao;
    private Double valor;
    private LocalDate data;

    public DetalhesReceitasDto(Receitas receitas){
        this.descricao = receitas.getDescricao();
        this.valor = receitas.getValor();
        this.data = receitas.getData();
    }
    public Page<DetalhesReceitasDto> converter(Page<Receitas> receitas){
        return receitas.map(DetalhesReceitasDto::new);
    }
}
