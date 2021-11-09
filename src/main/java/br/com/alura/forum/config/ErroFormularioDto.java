package br.com.alura.forum.config;

public class ErroFormularioDto {

    private String field;
    private String mensagem;

    public ErroFormularioDto(String field, String mensagem) {
        this.field = field;
        this.mensagem = mensagem;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
