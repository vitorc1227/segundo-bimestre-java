package br.edu.ifg.luziania.bsi.pw.model.dao;

import br.edu.ifg.luziania.bsi.pw.model.entities.Usuario;

import java.util.List;


public interface UsuarioDao {
    void createTable();
    Usuario insert(Usuario usuario);
    void deleteById(Long id);

    Integer deleteByEmail(String email);
    Usuario findById(Long id);
    Integer update(Usuario usuario);
    List<Usuario> findAll();

    Usuario existByEmailAndSenha(Usuario usuario);

    Integer updateRole(Short role, Long id);

    Usuario findByEmail(String email);
}
