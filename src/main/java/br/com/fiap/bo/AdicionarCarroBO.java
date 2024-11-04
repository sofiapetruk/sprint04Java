package br.com.fiap.bo;

import br.com.fiap.dao.AdicionarCarroDAO;
import br.com.fiap.to.AdicionarCarroTO;

import java.util.ArrayList;

public class AdicionarCarroBO {

    private AdicionarCarroDAO carroDAO;

    public ArrayList<AdicionarCarroTO> findAll() {
        carroDAO = new AdicionarCarroDAO();
        //aqui se implementa a regra de negócios
        return carroDAO.findAll();
    }

    public AdicionarCarroTO findById(Long idCarro) {
        carroDAO = new AdicionarCarroDAO();
        //aqui se implementa a regra de negócios
        return carroDAO.findById(idCarro);

    }
    public AdicionarCarroTO save(AdicionarCarroTO carro) {
        carroDAO = new AdicionarCarroDAO();
        //aqui se implementa a regra de negócios
        return carroDAO.save(carro);
    }

    public boolean delete(Long idCarro) {
        carroDAO = new AdicionarCarroDAO();
        //aqui se implementa a regra de negócios
        return carroDAO.delete(idCarro);
    }

    public AdicionarCarroTO uptade(AdicionarCarroTO carro) {
        carroDAO = new AdicionarCarroDAO();
        //aqui se implementa a regra de negócios
        return carroDAO.update(carro);
    }
}
