package br.com.brq.challenges.mercadinho.entrypoint.exception;

public class MensagemExceptionHandler {

    private String codigo;
    private String mensagem;
    private String urlErro;

    public MensagemExceptionHandler() {
    }

    public MensagemExceptionHandler(String codigo, String mensagem, String urlErro) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.urlErro = urlErro;
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
}
