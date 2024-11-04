package br.com.fiap.dao;

import br.com.fiap.to.OficinaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OficinaDAO extends  Repository{

    public ArrayList<OficinaTO> findAll() {

        ArrayList<OficinaTO> oficinas = new ArrayList<>();

        String sql = "SELECT * FROM t_cdb_oficina";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    OficinaTO oficina = new OficinaTO();

                    oficina.setIdOficina(rs.getLong("id_oficina"));
                    oficina.setNome(rs.getString("nome"));
                    oficina.setEndereco(rs.getString("endereco"));
                    oficina.setCNPJ(rs.getString("cnpj"));
                    oficina.setTelefone(rs.getString("telefone"));

                    oficinas.add(oficina);
                }
            } else {
                return null;
            }


        }catch (SQLException e) {
            System.out.println("Erro (SQL): " + e.getMessage());
        } finally {
            closeConnection();
        }
        return oficinas;
    }

    public OficinaTO findById(Long idOficina) {

        OficinaTO oficina = null;

        String sql = "SELECT * FROM t_cdb_oficina WHERE id_oficina = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idOficina);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    oficina = new OficinaTO();

                    oficina.setIdOficina(rs.getLong("id_oficina"));
                    oficina.setNome(rs.getString("nome"));
                    oficina.setEndereco(rs.getString("endereco"));
                    oficina.setCNPJ(rs.getString("cnpj"));
                    oficina.setTelefone(rs.getString("telefone"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return oficina;

    }

    public OficinaTO save(OficinaTO oficina) {
        String sql = "INSERT INTO t_cdb_oficina (id_oficina, nome, endereco, cnpj ,telefone) VALUES (t_sq_id_oficina.NEXTVAL, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, oficina.getNome());
            ps.setString(2, oficina.getEndereco());
            ps.setString(3, oficina.getCNPJ());
            ps.setString(4, oficina.getTelefone());

            if (ps.executeUpdate() > 0) {
                return oficina;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return  null;

    }

    public OficinaTO update(OficinaTO oficina) {
        String sql = "UPDATE t_cdb_oficina SET nome =?, endereco =?, cnpj =?, telefone = ? WHERE id_oficina =?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, oficina.getNome());
            ps.setString(2, oficina.getEndereco());
            ps.setString(3, oficina.getCNPJ());
            ps.setString(4, oficina.getTelefone());
            ps.setLong(5, oficina.getIdOficina());

            if (ps.executeUpdate() > 0) {
                return oficina;
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

    public boolean delete (Long idOficina) {
        String sql = "DELETE FROM t_cdb_oficina WHERE id_oficina =?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idOficina);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }



}
