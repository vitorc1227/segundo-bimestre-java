package br.edu.ifg.luziania.bsi.pw.rest.impl;

import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.EditalDTO;
import br.edu.ifg.luziania.bsi.pw.rest.EditalResource;
import br.edu.ifg.luziania.bsi.pw.service.EditalService;
import br.edu.ifg.luziania.bsi.pw.service.impl.EditalServiceImpl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class EditalResourceImpl implements EditalResource {

    private final EditalService editalService = new EditalServiceImpl();

    public Response findById(Long id) {
        return editalService.buscarPorId(id);
    }

    @Override
    public Response findAll() {
        return editalService.findAll();
    }

    @Override
    public Response cadastrar(EditalDTO editalDTO, UriInfo uriInfo) {
        return editalService.cadastrar(editalDTO, uriInfo);
    }

    @Override
    public Response atualizarEdital(Long id, EditalDTO editalDTO, UriInfo uriInfo) {
        return editalService.atualizarEdital(id, editalDTO, uriInfo);
    }

    @Override
    public Response deletar(Long id) {
        return editalService.deletar(id);
    }

}
