package br.edu.ifg.luziania.bsi.pw.rest.impl;

import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.NoticiaDTO;
import br.edu.ifg.luziania.bsi.pw.rest.NoticiaResource;
import br.edu.ifg.luziania.bsi.pw.service.NoticiaService;
import br.edu.ifg.luziania.bsi.pw.service.impl.NoticiaServiceImpl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class NoticiaResourceImpl implements NoticiaResource {

    private final NoticiaService noticiaService = new NoticiaServiceImpl();

    public Response findById(Long id) {
        return noticiaService.buscarPorId(id);
    }

    @Override
    public Response findAll() {
        return noticiaService.todosNoticias();
    }

    @Override
    public Response cadastrar(NoticiaDTO noticiaDTO, UriInfo uriInfo) {
        return noticiaService.cadastrar(noticiaDTO, uriInfo);
    }

    @Override
    public Response atualizarNoticia(Long id, NoticiaDTO noticiaDTO, UriInfo uriInfo) {
        return noticiaService.atualizarNoticia(id, noticiaDTO, uriInfo);
    }

    @Override
    public Response deletar(Long id) {
        return noticiaService.deletar(id);
    }

}
