package br.com.alura.forum.service;

import br.com.alura.forum.modelo.Topico;

import java.util.List;
import java.util.Optional;

public interface TopicoService {

    List<Topico> findAll();
    List<Topico> findAllByNomeCurso(String nomeCurso);
    Topico salvar(Topico topico);
    Optional<Topico> findById(Long id);
    Optional<Topico> deletar(Long id);
}
