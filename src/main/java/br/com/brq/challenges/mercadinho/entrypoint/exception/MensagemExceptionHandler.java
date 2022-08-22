package br.com.brq.challenges.mercadinho.entrypoint.exception;

import java.util.List;

public class MensagemExceptionHandler {

    private String codigo;
    private String mensagem;
    private String urlErro;
    private List<CampoMensagemExceptionHandler> campos;

    public MensagemExceptionHandler() {
    }

    public MensagemExceptionHandler(String codigo, String mensagem, String urlErro, List<CampoMensagemExceptionHandler> campos) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.urlErro = urlErro;
        this.campos = campos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUrlErro() {
        return urlErro;
    }

    public void setUrlErro(String urlErro) {
        this.urlErro = urlErro;
    }

    public List<CampoMensagemExceptionHandler> getCampos() {
        return campos;
    }

    public void setCampos(List<CampoMensagemExceptionHandler> campos) {
        this.campos = campos;
    }
}
