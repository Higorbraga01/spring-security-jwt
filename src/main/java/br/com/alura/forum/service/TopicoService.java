package br.com.alura.forum.service;

import br.com.alura.forum.modelo.Topico;

import java.util.List;

public interface TopicoService {

    List<Topico> findAll();
    List<Topico> findAllByNomeCurso(String nomeCurso);
    void salvar(Topico topico);
}
