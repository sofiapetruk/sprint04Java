package br.com.fiap.dao;


import br.com.fiap.to.AdicionarCarroTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdicionarCarroDAO extends Repository {

    public ArrayList<AdicionarCarroTO> findAll() {

        ArrayList<AdicionarCarroTO> carros = new ArrayList<>();
        String sql = "SELECT * FROM t_cdb_adicionar_carro ORDER BY id_carro";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    AdicionarCarroTO carro = new AdicionarCarroTO();

                    carro.setIdCarro(rs.getLong("id_carro"));
                    carro.setMarca(rs.getString("nm_marca"));
                    carro.setModelo(rs.getString("nm_modelo"));
                    carro.setAno(rs.getInt("nr_ano"));
                    carro.setQuilometragem(rs.getDouble("nr_quilometragem"));

                    carros.add(carro);
                }
            } else {
                return null;
            }


        }catch (SQLException e) {
            System.out.println("Erro (SQL): " + e.getMessage());
        } finally {
            closeConnection();
        }
        return carros;
    }

    public AdicionarCarroTO findById(Long idCarro) {
        AdicionarCarroTO carro = null;

        String sql = "SELECT * FROM t_cdb_adicionar_carro WHERE id_carro = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idCarro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    carro = new AdicionarCarroTO();

                    carro.setIdCarro(rs.getLong("id_carro"));
                    carro.setMarca(rs.getString("nm_marca"));
                    carro.setModelo(rs.getString("nm_modelo"));
                    carro.setAno(rs.getInt("nr_ano"));
                    carro.setQuilometragem(rs.getDouble("nr_quilometragem"));

                }

            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
       return carro;
    }

    public AdicionarCarroTO save(AdicionarCarroTO carro) {
        String sql = "INSERT INTO t_cdb_adicionar_carro (id_carro, nm_marca, nm_modelo, nr_ano, nr_quilometragem) VALUES (t_sq_id_carro.NEXTVAL, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, carro.getMarca());
            ps.setString(2, carro.getModelo());
            ps.setInt(3, carro.getAno());
            ps.setDouble(4, carro.getQuilometragem());

            if (ps.executeUpdate() > 0) {
                return carro;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return  null;


    }

    public boolean delete(Long idCarro) {
        String sql = "DELETE FROM t_cdb_adicionar_carro WHERE id_carro = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idCarro);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public AdicionarCarroTO update(AdicionarCarroTO carro) {
        String sql = "UPDATE t_cdb_adicionar_carro SET nm_marca=?, nm_modelo=?, nr_ano=?, nr_quilometragem=? WHERE id_carro = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, carro.getMarca());
            ps.setString(2, carro.getModelo());
            ps.setInt(3, carro.getAno());
            ps.setDouble(4, carro.getQuilometragem());
            ps.setLong(5, carro.getIdCarro());

            if (ps.executeUpdate() > 0) {
                return carro;
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
