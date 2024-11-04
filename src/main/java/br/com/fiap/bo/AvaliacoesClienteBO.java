package br.com.fiap.bo;

import br.com.fiap.dao.AvaliacaoClienteDAO;
import br.com.fiap.to.AvaliacaoClienteTO;

import java.util.ArrayList;

public class AvaliacoesClienteBO {

    private AvaliacaoClienteDAO avaliacaoClienteDAO;

    public ArrayList<AvaliacaoClienteTO> findAll() {
        avaliacaoClienteDAO = new AvaliacaoClienteDAO();
        //aqui se implementa a regra de negócios
        return avaliacaoClienteDAO.findAll();
    }

    public AvaliacaoClienteTO findById(Long idAvaliacao) {
        avaliacaoClienteDAO = new AvaliacaoClienteDAO();
        //aqui se implementa a regra de negócios
        return  avaliacaoClienteDAO.findById(idAvaliacao);
    }

    public AvaliacaoClienteTO save(AvaliacaoClienteTO avalicao) {
        avaliacaoClienteDAO = new AvaliacaoClienteDAO();
        //aqui se implementa a regra de negócios
        return avaliacaoClienteDAO.save(avalicao);

    }

    public boolean delete(Long idAvalicao) {
        avaliacaoClienteDAO = new AvaliacaoClienteDAO();
        //aqui se implementa a regra de negócios
        return  avaliacaoClienteDAO.delete(idAvalicao);
    }

    public AvaliacaoClienteTO update(AvaliacaoClienteTO avaliacao) {
        avaliacaoClienteDAO = new AvaliacaoClienteDAO();
        //aqui se implementa a regra de negócios
        return avaliacaoClienteDAO.updtade(avaliacao);
    }
}
