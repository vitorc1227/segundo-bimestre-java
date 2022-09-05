package br.edu.ifg.luziania.bsi.pw.service;

import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.NoticiaDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public interface NoticiaService {

    void criarTabela();

    Response cadastrar(NoticiaDTO noticiaDTO, UriInfo uriInfo);

    Response buscarPorId(Long id);

    Response todosNoticias();

    Response atualizarNoticia(Long id, NoticiaDTO noticiaDTO, UriInfo uriInfo);

    Response deletar(Long id);
}
