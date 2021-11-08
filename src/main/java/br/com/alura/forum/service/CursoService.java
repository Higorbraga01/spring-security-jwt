package br.com.alura.forum.service;

import br.com.alura.forum.modelo.Curso;

public interface CursoService {
   Curso findByNome(String nome);
}
