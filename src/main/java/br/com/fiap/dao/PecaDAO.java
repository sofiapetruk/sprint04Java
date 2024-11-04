package br.com.fiap.dao;

import br.com.fiap.to.PecaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PecaDAO extends Repository{

    public ArrayList<PecaTO> findAll() {

        ArrayList<PecaTO> pecas = new ArrayList<>();
        String sql = "SELECT * FROM t_cdb_peca ORDER BY id_peca";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    PecaTO peca = new PecaTO();
                    peca.setIdPeca(rs.getLong("id_peca"));
                    peca.setNomePeca(rs.getString("nm_peca"));
                    peca.setPrecoPeca(rs.getDouble("pr_peca"));
                    pecas.add(peca);
                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return pecas;

    }

    public PecaTO findByCodigo(Long idPeca) {
        PecaTO peca = null;

        String sql = "SELECT * FROM t_cdb_peca WHERE id_peca = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idPeca);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    peca = new PecaTO();

                    peca.setIdPeca(rs.getLong("id_peca"));
                    peca.setNomePeca(rs.getString("nm_peca"));
                    peca.setPrecoPeca(rs.getDouble("pr_peca"));
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return peca;
    }

    public PecaTO save (PecaTO peca) {
        String sql = "INSERT INTO t_cdb_peca (id_peca, nm_peca, pr_peca) VALUES (t_sq_id_peca.NEXTVAL, ?,?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, peca.getNomePeca());
            ps.setDouble(2, peca.getPrecoPeca());

            if (ps.executeUpdate() > 0) {
                return peca;
            }


        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return  null;
    }

    public boolean delete(Long idPeca) {

        String sql = "DELETE FROM t_cdb_peca WHERE id_peca = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setLong(1, idPeca);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public double orcamentoMedioPeca(String nomePeca) {
        String sql = "SELECT AVG(preco_peca) AS media_precos FROM cha_peca WHERE LOWER(nome_peca) = LOWER(?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, nomePeca);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("media_precos");
                } else {
                    throw new IllegalArgumentException("Nenhuma peça encontrada com o nome especificado: " + nomePeca);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao calcular o orçamento médio: " + e.getMessage());
        }
        return 0.0;
    }

    public PecaTO updtade(PecaTO peca) {
        String sql = "UPDATE t_cdb_peca SET nm_peca=?, pr_peca=? WHERE id_peca = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, peca.getNomePeca());
            ps.setDouble(2, peca.getPrecoPeca());
            ps.setLong(3, peca.getIdPeca());

            if (ps.executeUpdate() > 0) {
                return peca;
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
