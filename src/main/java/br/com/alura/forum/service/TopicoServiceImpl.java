package br.com.alura.forum.service;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoServiceImpl implements TopicoService{


    private TopicoRepository repository;

    public TopicoServiceImpl(TopicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Topico> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Topico> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Topico> findAllByNomeCurso(String nomeCurso, Pageable pageable) {
        return repository.findByCurso_Nome(nomeCurso, pageable);
    }

    @Override
    public Topico salvar(Topico topico) {
        return repository.save(topico);
    }

    @Override
    public Optional<Topico> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Topico> deletar(Long id) {
        Optional<Topico> found = repository.findById(id);
        if(found.isPresent()) {
            repository.deleteById(id);
        }else
            return found;
        return found;
    }
}
