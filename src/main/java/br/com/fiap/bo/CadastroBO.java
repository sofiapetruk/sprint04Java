package br.com.fiap.bo;

import br.com.fiap.dao.CadastroDAO;
import br.com.fiap.to.CadastroTO;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CadastroBO {

    private CadastroDAO cadastroDAO;

    public ArrayList<CadastroTO> findAll() {
        cadastroDAO = new CadastroDAO();
        //aqui se implementa a regra de negócios
        return cadastroDAO.findAll();
    }

    public CadastroTO findByCodigo(Long idCliente) {
        cadastroDAO = new CadastroDAO();
        // aqui se implementa a regra de negócios
        return cadastroDAO.findByCodigo(idCliente);
    }

    public CadastroTO save(CadastroTO cadastro) throws Exception{

        cadastroDAO = new CadastroDAO();
        // aqui se implementa a regra de negócios
        if (cadastro.getSenha().length() <= 8) {
            throw new Exception("Sua senha passou da quantidade. Só pode ter menor que 8 caractere");
        }

        return cadastroDAO.save(cadastro);
    }

    public boolean delete(Long idCliente) {
        cadastroDAO = new CadastroDAO();
        //aqui se implementa a regra de negocio
        return cadastroDAO.delete(idCliente);

    }

    public CadastroTO updtade(CadastroTO cadastro) {
        cadastroDAO = new CadastroDAO();
        //aqui se implementa a regra de negocio
        return cadastroDAO.update(cadastro);
    }

}
