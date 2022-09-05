package br.edu.ifg.luziania.bsi.pw.model.dao;

import br.edu.ifg.luziania.bsi.pw.model.entities.Noticia;
import br.edu.ifg.luziania.bsi.pw.model.entities.Usuario;

import java.util.List;


public interface NoticiaDao {
    void createTable();
    Noticia insert(Noticia usuario);
    Integer deleteById(Long id);
    Noticia findById(Long id);
    Integer update(Noticia usuario);
    List<Noticia> findAll();

}
