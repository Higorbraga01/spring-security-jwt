package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicosController {

    @Autowired
    private TopicoService service;

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(String nomeCurso) {
        if(nomeCurso == null){
            List<Topico> topicos = service.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> cursosByNome = service.findAllByNomeCurso(nomeCurso);
            return TopicoDto.converter(cursosByNome);
        }

    }

}
