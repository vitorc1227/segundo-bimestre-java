package br.edu.ifg.luziania.bsi.pw.service;

import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.EditalDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public interface EditalService {

    void criarTabela();

    Response cadastrar(EditalDTO noticiaDTO, UriInfo uriInfo);

    Response buscarPorId(Long id);

    Response findAll();

    Response atualizarEdital(Long id, EditalDTO noticiaDTO, UriInfo uriInfo);

    Response deletar(Long id);
}
