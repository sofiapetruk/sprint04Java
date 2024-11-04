package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class AvaliacaoClienteTO {
    private Long idAvaliacao;
    @NotBlank private String nomeCliente;
    @NotNull @Min(0) @Max(5)
    private int avaliacao;
    @NotBlank private String comentario;

    //construtor
    public AvaliacaoClienteTO() {
    }

    public AvaliacaoClienteTO(Long idAvaliacao, @NotBlank String nomeCliente, @NotNull @Min(0) @Max(5) int avaliacao, @NotBlank String comentario) {
        this.idAvaliacao = idAvaliacao;
        this.nomeCliente = nomeCliente;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
    }


    //getters e setters
    public Long getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Long idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
