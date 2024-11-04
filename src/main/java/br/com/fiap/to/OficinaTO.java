package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

public class OficinaTO {
    private Long idOficina;
    @NotBlank private String nome;
    @NotBlank private String endereco;
    @NotBlank private String CNPJ;
    private String telefone;


    public OficinaTO() {
    }

    public OficinaTO(Long id_oficina, @NotBlank String nome, @NotBlank String endereco, @NotBlank String CNPJ, String telefone) {
        this.idOficina = id_oficina;
        this.nome = nome;
        this.endereco = endereco;
        this.CNPJ = CNPJ;
        this.telefone = telefone;
    }

    public Long getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Long idOficina) {
        this.idOficina = idOficina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
