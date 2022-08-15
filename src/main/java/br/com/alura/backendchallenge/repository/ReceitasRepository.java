package br.com.alura.backendchallenge.repository;

import br.com.alura.backendchallenge.model.Receitas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReceitasRepository extends JpaRepository<Receitas, Long> {

    @Query("select (count(r) > 0) from Receitas r where r.descricao = ?1")
    boolean existsByDescricao(String descricao);

//    @Query("select (count(r) > 0) from Receitas r where r.descricao =?1 and month(r.data) = current_date ")
//    boolean existsByDescricaoEMes(String descricao, LocalDate data);

    Page<Receitas> findByDescricao(String descricao, Pageable paginacao);
}
