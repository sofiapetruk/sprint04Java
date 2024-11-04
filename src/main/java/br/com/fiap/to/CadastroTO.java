package br.com.fiap.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CadastroTO {
    private Long idCliente;
    @NotBlank private String nomeCompleto;
    @NotBlank private String email;
    @NotBlank private String senha;  //não precisa colocar as validações aqui do cadastro pq no front já está feito
    @NotBlank private String numeroTelefone;
    @NotNull private LocalDate dataNascimento;
    @NotBlank private String cep;

    //construtores
    public CadastroTO() {
    }

    public CadastroTO(Long idCliente, @NotBlank String nomeCompleto, @NotBlank String email, @NotBlank String senha, @NotBlank String numeroTelefone, @NotNull LocalDate dataNascimento, @NotBlank String cep) {
        this.idCliente = idCliente;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.numeroTelefone = numeroTelefone;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
    }

    //getters e setters
    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCep() {
        return cep;
    }
    public void setCep( String cep) {
        this.cep = cep;
    }
}

