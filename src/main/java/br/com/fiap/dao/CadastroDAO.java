package br.com.fiap.dao;

import br.com.fiap.to.CadastroTO;
import br.com.fiap.to.OficinaTO;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CadastroDAO extends Repository {

    public ArrayList<CadastroTO> findAll() {

        ArrayList<CadastroTO> cadastros = new ArrayList<>();
        String sql = "SELECT * FROM chaCadastroCliente ORDER BY id_cliente";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    CadastroTO cadastro = new CadastroTO();
                    cadastro.setIdCliente(rs.getLong("id_cliente"));
                    cadastro.setNomeCompleto(rs.getString("nome_completo"));
                    cadastro.setEmail(rs.getString("email"));
                    cadastro.setSenha(rs.getString("senha"));
                    cadastro.setNumeroTelefone(rs.getString("numero_telefone"));
                    cadastro.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                    cadastro.setCep(rs.getString("cep"));
                    cadastros.add(cadastro);
                }

            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return cadastros;

    }

    public CadastroTO findByCodigo(Long idCliente) {
        CadastroTO cadastro = null;

        String sql = "SELECT * FROM chaCadastroCliente WHERE id_cliente = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cadastro = new CadastroTO();
                    cadastro.setIdCliente(rs.getLong("id_cliente"));
                    cadastro.setNomeCompleto(rs.getString("nome_completo"));
                    cadastro.setEmail(rs.getString("email"));
                    cadastro.setSenha(rs.getString("senha"));
                    cadastro.setNumeroTelefone(rs.getString("numero_telefone"));
                    cadastro.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                    cadastro.setCep(rs.getString("cep"));

                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return cadastro;
    }

    public CadastroTO save (CadastroTO cadastro) {
        String sql = "INSERT INTO chaCadastroCliente(nome_completo, email, senha, numero_telefone, data_nascimento, cep) VALUES(?, ?, ?, ?, ?, ?) ";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, cadastro.getNomeCompleto());
            ps.setString(2, cadastro.getEmail());
            ps.setString(3, cadastro.getSenha());
            ps.setString(4, cadastro.getNumeroTelefone());
            ps.setDate(5, Date.valueOf(cadastro.getDataNascimento()));
            ps.setString(6, cadastro.getCep());

            if (ps.executeUpdate() > 0) {
                return cadastro;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return  null;
    }

    public boolean delete(Long idCliente) {

        String sql = "delete from chaCadastroCliente WHERE id_cliente = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setLong(1, idCliente);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;

    }

    public  CadastroTO update(CadastroTO cadastro) {
        String sql = "UPDATE chaCadastroCliente SET nome_completo=?, email=?, senha=?, numero_telefone=?, data_nascimento=?, cep=? WHERE id_cliente = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, cadastro.getNomeCompleto());
            ps.setString(2, cadastro.getEmail());
            ps.setString(3, cadastro.getSenha());
            ps.setString(4, cadastro.getNumeroTelefone());
            ps.setDate(5, Date.valueOf(cadastro.getDataNascimento()));
            ps.setLong(6, cadastro.getIdCliente());
            ps.setString(7, cadastro.getCep());

            if (ps.executeUpdate() > 0) {
                return cadastro;
            } else {
                return null;
            }


        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }


}
