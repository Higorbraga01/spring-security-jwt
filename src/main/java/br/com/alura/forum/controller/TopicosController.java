package br.com.alura.forum.controller;

import br.com.alura.forum.dto.AtualizacaoTopicoForm;
import br.com.alura.forum.dto.DetalhesTopicoDto;
import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.dto.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.service.CursoService;
import br.com.alura.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoService service;

    @Autowired
    private CursoService cursoService;

    @GetMapping()
    public List<TopicoDto> lista(String nomeCurso) {
        if(nomeCurso == null){
            List<Topico> topicos = service.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> cursosByNome = service.findAllByNomeCurso(nomeCurso);
            return TopicoDto.converter(cursosByNome);
        }

    }

    @PostMapping()
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@Valid @RequestBody TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.converter(cursoService);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        Topico saved = service.salvar(topico);
        return ResponseEntity.created(uri).body(new TopicoDto(saved));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable Long id) {
        Topico topico = service.findById(id);
        return ResponseEntity.ok(new DetalhesTopicoDto(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@Valid @RequestBody AtualizacaoTopicoForm atualizacaoTopicoForm, UriComponentsBuilder uriBuilder, @PathVariable Long id) {
        Topico topico = atualizacaoTopicoForm.atualizar(id, service);
        return ResponseEntity.ok().body(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> remover(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
