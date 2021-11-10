package br.com.alura.forum.service;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TopicoService {

    List<Topico> findAll();
    Page<Topico> findAll(Pageable pageable);
    Page<Topico> findAllByNomeCurso(String nomeCurso, Pageable pageable);
    Topico salvar(Topico topico);
    Optional<Topico> findById(Long id);
    Optional<Topico> deletar(Long id);
}
