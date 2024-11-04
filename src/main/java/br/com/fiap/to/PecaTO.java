package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Set;

public class PecaTO {
    private Long idPeca;
    @NotBlank private String nomePeca;
    @NotNull @PositiveOrZero
    private double precoPeca;

    //construtores
    public PecaTO() {
    }

    public PecaTO(Long idPeca, @NotBlank String nomePeca, @NotNull @PositiveOrZero double precoPeca) {
        this.idPeca = idPeca;
        this.nomePeca = nomePeca;
        this.precoPeca = precoPeca;
    }

    //getters e setters
    public Long getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public double getPrecoPeca() {
        return precoPeca;
    }

    public void setPrecoPeca(double precoPeca) {
        this.precoPeca = precoPeca;
    }

    //método
    public double orcamentoMedioPeca(Set<PecaTO> pecas, String nomePeca) {
        if (pecas == null || pecas.isEmpty()) {
            throw new IllegalArgumentException("O conjunto de peças não pode estar vazio.");
        }

        float soma = 0;
        int cont = 0;

        for (PecaTO peca: pecas) {
            if (peca.getNomePeca().equalsIgnoreCase(nomePeca)) { // Compara ignorando maiúsculas/minúsculas
                soma += peca.getPrecoPeca();
                cont++;
            }
        }

        if (cont == 0) {
            throw new IllegalArgumentException("Nenhuma peça encontrada com o nome especificado: " + nomePeca);
        }

        return soma / cont;
    }
}
