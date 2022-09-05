package br.edu.ifg.luziania.bsi.pw.model.dao.impl;

import br.edu.ifg.luziania.bsi.pw.db.Db;
import br.edu.ifg.luziania.bsi.pw.db.DbException;
import br.edu.ifg.luziania.bsi.pw.db.DbImpl;
import br.edu.ifg.luziania.bsi.pw.model.dao.NoticiaDao;
import br.edu.ifg.luziania.bsi.pw.model.dao.UsuarioDao;
import br.edu.ifg.luziania.bsi.pw.model.entities.Noticia;
import br.edu.ifg.luziania.bsi.pw.model.entities.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//@ManagedBean
public class NoticiaDaoImpl implements NoticiaDao {


    //    @Inject
    private final Db db = new DbImpl();

    @Override
    public void createTable() {
        PreparedStatement st = null;

        try {
            st = db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS NOTICIA "
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
    public Noticia insert(Noticia noticia) {
        PreparedStatement st = null;

        try {
            st = db.getConnection().prepareStatement("INSERT INTO NOTICIA (nome, descricao) " +
                    "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, noticia.getNome());
            st.setString(2, noticia.getDescricao());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    noticia.setId(id);

                }
                db.closeResultSet(rs);
                return noticia;
            } else {
                throw new DbException("Nenhuma linha foi afetada!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeStatement(st);
        }

        return new Noticia();
    }

    @Override
    public Integer update(Noticia noticia) {
        PreparedStatement st = null;
        try {
            st = db.getConnection().prepareStatement(
                    "UPDATE NOTICIA "
                            + "SET nome = ?, descricao = ?"
                            + "WHERE id = ?");

            st.setString(1, noticia.getNome());
            st.setString(2, noticia.getDescricao());
            st.setLong(3, noticia.getId());

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
            st = db.getConnection().prepareStatement("DELETE FROM NOTICIA WHERE id = ?");

            st.setLong(1, id);

            return st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
        }
    }


    @Override
    public Noticia findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = db.getConnection().prepareStatement(
                    "SELECT * "
                            + "FROM NOTICIA "
                            + "WHERE id = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateNoticia(rs);
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
    public List<Noticia> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = db.getConnection().prepareStatement(
                    "SELECT * "
                            + "FROM NOTICIA "
                            + "ORDER BY id");

            rs = st.executeQuery();
            List<Noticia> list = new ArrayList<>();

            while (rs.next()) {
                Noticia obj = instantiateNoticia(rs);
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

    private Noticia instantiateNoticia(ResultSet rs) throws SQLException {
        Noticia noticia = new Noticia();
        noticia.setId(rs.getLong("id"));
        noticia.setNome(rs.getString("nome"));
        noticia.setDescricao(rs.getString("descricao"));
        return noticia;
    }

}
