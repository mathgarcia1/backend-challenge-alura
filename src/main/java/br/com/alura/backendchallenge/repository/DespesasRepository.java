package br.com.alura.backendchallenge.repository;

import br.com.alura.backendchallenge.model.Despesas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesasRepository extends JpaRepository<Despesas, Long> {
    Page<Despesas> findByDescricao(String descricao, Pageable paginacao);
}
