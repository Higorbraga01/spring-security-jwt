package br.com.alura.forum.service;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements  CursoService{

    CursoRepository repository;

    public CursoServiceImpl(CursoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Curso findByNome(String nome) {
        return repository.findByNome(nome);
    }
}
