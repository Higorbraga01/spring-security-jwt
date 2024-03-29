package br.com.alura.forum.controller;

import br.com.alura.forum.dto.AtualizacaoTopicoForm;
import br.com.alura.forum.dto.DetalhesTopicoDto;
import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.dto.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.service.CursoService;
import br.com.alura.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoService service;

    @Autowired
    private CursoService cursoService;

    @GetMapping()
    @Cacheable(value = "listaDeTopicos")
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        if(nomeCurso == null){
            Page<Topico> topicos = service.findAll(pageable);
            return TopicoDto.converter(topicos);
        } else {
            Page<Topico> cursosByNome = service.findAllByNomeCurso(nomeCurso, pageable);
            return TopicoDto.converter(cursosByNome);
        }

    }

    @PostMapping()
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> cadastrar(@Valid @RequestBody TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.converter(cursoService);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        Topico saved = service.salvar(topico);
        return ResponseEntity.created(uri).body(new TopicoDto(saved));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable Long id) {
        Optional<Topico> found = service.findById(id);
        if(found.isPresent()){
            return ResponseEntity.ok(new DetalhesTopicoDto(found.get()));
        }else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> atualizar(@Valid @RequestBody AtualizacaoTopicoForm atualizacaoTopicoForm, UriComponentsBuilder uriBuilder, @PathVariable Long id) {
        Optional<Topico> topico = atualizacaoTopicoForm.atualizar(id, service);
        if(topico.isPresent()){
            return ResponseEntity.ok().body(new TopicoDto(topico.get()));
        }else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> remover(@PathVariable Long id) {
        Optional<Topico> found = service.deletar(id);
        if(found.isPresent()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.notFound().build();
    }
}
