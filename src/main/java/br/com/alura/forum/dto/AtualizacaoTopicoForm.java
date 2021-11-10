package br.com.alura.forum.dto;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.service.TopicoService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AtualizacaoTopicoForm {

    @NotNull
    @NotEmpty
    private String titulo;
    @NotNull
    @NotEmpty
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Optional<Topico> atualizar(Long id, TopicoService service) {
        Optional<Topico> topico = service.findById(id);
        if(topico.isPresent()){
            topico.get().setTitulo(this.titulo);
            topico.get().setMensagem(this.mensagem);
        }
        return topico;
    }
}
