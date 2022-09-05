package br.edu.ifg.luziania.bsi.pw.model.dao.impl;

import br.edu.ifg.luziania.bsi.pw.db.Db;
import br.edu.ifg.luziania.bsi.pw.db.DbException;
import br.edu.ifg.luziania.bsi.pw.db.DbImpl;
import br.edu.ifg.luziania.bsi.pw.model.entities.Usuario;
import br.edu.ifg.luziania.bsi.pw.model.dao.UsuarioDao;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@ManagedBean
public class UsuarioDaoImpl implements UsuarioDao {


//    @Inject
    private final Db db = new DbImpl();

    @Override
    public void createTable() {
        PreparedStatement st = null;

        try {
            st = db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS USUARIO "
                   + " (id INT PRIMARY KEY AUTO_INCREMENT, "
                   + " nome VARCHAR(255) not null, "
                   + " email VARCHAR(50) unique not null, "
                   + " cargo INTEGER not null, "
                   + " senha VARCHAR(50) not null, "
                   + " PRIMARY KEY (id)); ");

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
        }

    }

    @Override
    public Usuario insert(Usuario usuario) {
        PreparedStatement st = null;

        try {
            st = db.getConnection().prepareStatement("INSERT INTO USUARIO (nome, email, cargo, senha) " +
                    "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setShort(3, usuario.getCargo());
            st.setString(4, usuario.getSenha());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    usuario.setId(id);

                }
                db.closeResultSet(rs);
                return usuario;
            } else {
                throw new DbException("Nenhuma linha foi afetada!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeStatement(st);
        }

        return new Usuario();
    }

    @Override
    public Integer update(Usuario usuario) {
        PreparedStatement st = null;
        try {
            st = db.getConnection().prepareStatement(
                    "UPDATE USUARIO "
                            + "SET nome = ?, email = ?, senha = ?, cargo = ?"
                            + "WHERE id = ?");

            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getSenha());
            st.setShort(5, usuario.getCargo());
            st.setLong(4, usuario.getId());

            return st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement st = null;
        try {
            st = db.getConnection().prepareStatement("DELETE FROM USUARIO WHERE id = ?");

            st.setLong(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
        }
    }

    @Override
    public Integer deleteByEmail(String email) {
        PreparedStatement st = null;
        try {
            st = db.getConnection().prepareStatement("DELETE FROM USUARIO WHERE email = ?");

            st.setString(1, email);

            return st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
        }
    }

    @Override
    public Usuario findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = db.getConnection().prepareStatement(
                    "SELECT * "
                            + "FROM USUARIO "
                            + "WHERE id = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateUser(rs);
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
    public List<Usuario> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = db.getConnection().prepareStatement(
                    "SELECT * "
                            + "FROM USUARIO "
                            + "ORDER BY id");

            rs = st.executeQuery();
            List<Usuario> list = new ArrayList<>();

            while (rs.next()) {
                Usuario obj = instantiateUser(rs);
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

    @Override
    public Usuario existByEmailAndSenha(Usuario usuario) {
        return findByEmailAndPassword(usuario);
    }

    @Override
    public Integer updateRole(Short role, Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = db.getConnection().prepareStatement(
                    "UPDATE USUARIO "
                            + "SET cargo = ? "
                            + "WHERE id = ?");

            st.setShort(1, role);
            st.setLong(2, id);

            return st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            db.closeStatement(st);
            db.closeResultSet(rs);
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = db.getConnection().prepareStatement(
                    "SELECT * "
                            + "FROM USUARIO "
                            + "WHERE email = ?");

            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiateUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeResultSet(rs);;
            db.closeStatement(st);
        }

        return new Usuario();
    }


    private Usuario findByEmailAndPassword(Usuario usuario) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = db.getConnection().prepareStatement(
                    "SELECT * "
                            + "FROM USUARIO "
                            + "WHERE email = ? AND senha = ?");

            st.setString(1, usuario.getEmail());
            st.setString(2, usuario.getSenha());
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiateUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeResultSet(rs);;
            db.closeStatement(st);
        }

        return new Usuario();
    }


    private Usuario instantiateUser(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setCargo(rs.getShort("cargo"));
        usuario.setSenha(rs.getString("senha"));
        return usuario;
    }

}
