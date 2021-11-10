package br.com.alura.forum.service;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;
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
    public List<Topico> findAllByNomeCurso(String nomeCurso) {
        return repository.findByCurso_Nome(nomeCurso);
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
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
