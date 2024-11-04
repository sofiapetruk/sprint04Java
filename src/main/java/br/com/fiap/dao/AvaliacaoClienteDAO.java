package br.com.fiap.dao;

import br.com.fiap.to.AvaliacaoClienteTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AvaliacaoClienteDAO extends Repository {

    public ArrayList<AvaliacaoClienteTO> findAll() {
        ArrayList<AvaliacaoClienteTO> avaliacoes = new ArrayList<>();
        String sql = "SELECT * FROM t_cdb_feedback order by id_feedback";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AvaliacaoClienteTO avaliacao = new AvaliacaoClienteTO();
                avaliacao.setIdAvaliacao(rs.getLong("id_feedback"));
                avaliacao.setNomeCliente(rs.getString("nome_cliente"));
                avaliacao.setAvaliacao(rs.getInt("nt_feedback"));
                avaliacao.setComentario(rs.getString("ds_feedback"));
                avaliacoes.add(avaliacao);
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return avaliacoes;
    }


    public AvaliacaoClienteTO findById(Long idAvaliacao) {

        AvaliacaoClienteTO avaliacao = null;
        String sql = "SELECT * FROM t_cdb_feedback WHERE id_feedback = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idAvaliacao);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    avaliacao = new AvaliacaoClienteTO();

                    avaliacao.setIdAvaliacao(rs.getLong("id_feedback"));
                    avaliacao.setNomeCliente(rs.getString("nome_cliente"));
                    avaliacao.setAvaliacao(rs.getInt("nt_feedback"));
                    avaliacao.setComentario(rs.getString("ds_feedback"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return  avaliacao;
    }

    public AvaliacaoClienteTO save(AvaliacaoClienteTO avaliacao) {
        String sql = "INSERT INTO t_cdb_feedback (id_feedback, nome_cliente, nt_feedback, ds_feedback)VALUES (t_sq_id_feedback.NEXTVAL, ?,?,?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, avaliacao.getNomeCliente());
            ps.setInt(2, avaliacao.getAvaliacao());
            ps.setString(3, avaliacao.getComentario());

            if (ps.executeUpdate() > 0 ) {
                return avaliacao;
            }

        }  catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return  null;
    }

    public boolean delete(Long idAvaliacao) {
        String sql = "DELETE FROM t_cdb_feedback WHERE id_feedback = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idAvaliacao);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public AvaliacaoClienteTO updtade(AvaliacaoClienteTO avaliacao) {
        String sql = "UPDATE t_cdb_feedback SET nome_cliente=?, nt_feedback=?, ds_feedback=? WHERE id_feedback = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, avaliacao.getNomeCliente());
            ps.setInt(2, avaliacao.getAvaliacao());
            ps.setString(3, avaliacao.getComentario());
            ps.setLong(4, avaliacao.getIdAvaliacao());

            if (ps.executeUpdate() > 0) {
                return avaliacao;
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
