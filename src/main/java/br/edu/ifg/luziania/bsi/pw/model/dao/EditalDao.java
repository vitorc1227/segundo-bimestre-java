package br.edu.ifg.luziania.bsi.pw.model.dao;

import br.edu.ifg.luziania.bsi.pw.model.entities.Edital;
import br.edu.ifg.luziania.bsi.pw.model.entities.Noticia;

import java.util.List;


public interface EditalDao {
    void createTable();
    Edital insert(Edital usuario);
    Integer deleteById(Long id);
    Edital findById(Long id);
    Integer update(Edital usuario);
    List<Edital> findAll();

}
