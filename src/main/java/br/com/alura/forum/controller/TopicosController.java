package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.dto.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.service.CursoService;
import br.com.alura.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void cadastrar(@RequestBody TopicoForm topicoForm) {
        Topico topico = topicoForm.converter(cursoService);
        service.salvar(topico);
    }

}
