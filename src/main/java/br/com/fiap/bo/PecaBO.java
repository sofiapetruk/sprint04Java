package br.com.fiap.bo;

import br.com.fiap.dao.PecaDAO;
import br.com.fiap.to.PecaTO;

import java.util.ArrayList;

public class PecaBO {

    private PecaDAO pecaDAO;

    public ArrayList<PecaTO> findAll() {
        pecaDAO = new PecaDAO();
        //aqui se implementa a regra de negócios
        return pecaDAO.findAll();
    }

    public PecaTO findByCodigo(Long idPeca) {
        pecaDAO = new PecaDAO();
        //aqui se implementa a regra de negócios
        return pecaDAO.findByCodigo(idPeca);
    }

    public PecaTO save(PecaTO peca) {
        pecaDAO = new PecaDAO();
        //aqui se implementa a regra de negócios
        return pecaDAO.save(peca);
    }

    public boolean delete(Long idAvaliacao) {
        pecaDAO = new PecaDAO();
        //aqui se implementa a regra de negocio
        return pecaDAO.delete(idAvaliacao);
    }

    public double media(String nomePeca) {
        pecaDAO = new PecaDAO();
        //aqui se implementa a regra de negocio
        return pecaDAO.orcamentoMedioPeca(nomePeca);
    }

    public PecaTO update(PecaTO peca) {
        pecaDAO = new PecaDAO();
        //aqui se implementa a regra de negocio
        return pecaDAO.updtade(peca);
    }

}
