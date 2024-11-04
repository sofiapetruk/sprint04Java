package br.com.fiap.bo;

import br.com.fiap.dao.OficinaDAO;
import br.com.fiap.to.OficinaTO;

import java.util.ArrayList;

public class OficinaBO {
    private OficinaDAO oficinaDAO;

    public ArrayList<OficinaTO> findAll() {
        oficinaDAO = new OficinaDAO();
        return oficinaDAO.findAll();
    }

    public OficinaTO findById(Long idOficina) {
        oficinaDAO = new OficinaDAO();
        return oficinaDAO.findById(idOficina);
    }

    public OficinaTO save(OficinaTO oficina) {
        oficinaDAO = new OficinaDAO();
        return oficinaDAO.save(oficina);
    }

    public OficinaTO update(OficinaTO oficina) {
        oficinaDAO = new OficinaDAO();
        return oficinaDAO.update(oficina);
    }

    public boolean delete(Long idOficina) {
        oficinaDAO = new OficinaDAO();
        return oficinaDAO.delete(idOficina);
    }
}
