package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class AdicionarCarroTO {

    private Long idCarro;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotNull
    private Integer ano;

    @NotNull @PositiveOrZero
    private Double quilometragem;

    // Construtores
    public AdicionarCarroTO() {}

    public AdicionarCarroTO(Long idCarro, @NotBlank String marca, @NotBlank String modelo, @NotNull Integer ano, @NotNull @PositiveOrZero Double quilometragem) {
        this.idCarro = idCarro;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.quilometragem = quilometragem;
    }

    // Getters e Setters
    public Long getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(Long idCarro) {
        this.idCarro = idCarro;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Double quilometragem) {
        this.quilometragem = quilometragem;
    }
}
