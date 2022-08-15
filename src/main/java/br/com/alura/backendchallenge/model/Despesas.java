package br.com.alura.backendchallenge.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "despesas")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Despesas {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data")
    private LocalDate data;
}
