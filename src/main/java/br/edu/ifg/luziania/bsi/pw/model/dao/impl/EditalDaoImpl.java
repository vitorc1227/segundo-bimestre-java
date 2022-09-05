package br.edu.ifg.luziania.bsi.pw.model.dao.impl;

import br.edu.ifg.luziania.bsi.pw.db.Db;
import br.edu.ifg.luziania.bsi.pw.db.DbException;
import br.edu.ifg.luziania.bsi.pw.db.DbImpl;
import br.edu.ifg.luziania.bsi.pw.model.dao.EditalDao;
import br.edu.ifg.luziania.bsi.pw.model.dao.NoticiaDao;
import br.edu.ifg.luziania.bsi.pw.model.entities.Edital;
import br.edu.ifg.luziania.bsi.pw.model.entities.Noticia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EditalDaoImpl implements EditalDao {


    private final Db db = new DbImpl();

    @Override
    public void createTable() {
        PreparedStatement st = null;

        try {
            st = db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS EDITAL "
                    + " (id INT PRIMARY KEY AUTO_INCREMENT, "
                    + " nome VARCHAR(255) not null, "
                    + " descricao VARCHAR(255) not null, "
                    + " PRIMARY KEY (id)); ");

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
        }

    }

    @Override
    public Edital insert(Edital edital) {
        PreparedStatement st = null;

        try {
            st = db.getConnection().prepareStatement("INSERT INTO EDITAL (nome, descricao) " +
                    "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, edital.getNome());
            st.setString(2, edital.getDescricao());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    edital.setId(id);

                }
                db.closeResultSet(rs);
                return edital;
            } else {
                throw new DbException("Nenhuma linha foi afetada!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeStatement(st);
        }

        return new Edital();
    }

    @Override
    public Integer update(Edital edital) {
        PreparedStatement st = null;
        try {
            st = db.getConnection().prepareStatement(
                    "UPDATE EDITAL "
                            + "SET nome = ?, descricao = ?"
                            + "WHERE id = ?");

            st.setString(1, edital.getNome());
            st.setString(2, edital.getDescricao());
            st.setLong(3, edital.getId());

            return st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
        }
    }

    @Override
    public Integer deleteById(Long id) {
        PreparedStatement st = null;
        try {
            st = db.getConnection().prepareStatement("DELETE FROM EDITAL WHERE id = ?");

            st.setLong(1, id);

            return st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
        }
    }


    @Override
    public Edital findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = db.getConnection().prepareStatement(
                    "SELECT * "
                            + "FROM EDITAL "
                            + "WHERE id = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateEdital(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
            db.closeResultSet(rs);
        }
    }

    @Override
    public List<Edital> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = db.getConnection().prepareStatement(
                    "SELECT * "
                            + "FROM EDITAL "
                            + "ORDER BY id");

            rs = st.executeQuery();
            List<Edital> list = new ArrayList<>();

            while (rs.next()) {
                Edital obj = instantiateEdital(rs);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
            db.closeResultSet(rs);
        }
    }

    private Edital instantiateEdital(ResultSet rs) throws SQLException {
        Edital edital = new Edital();
        edital.setId(rs.getLong("id"));
        edital.setNome(rs.getString("nome"));
        edital.setDescricao(rs.getString("descricao"));
        return edital;
    }

}
