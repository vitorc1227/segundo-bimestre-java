package br.edu.ifg.luziania.bsi.pw.rest;

import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.NoticiaDTO;
import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.UsuarioCadastrarDTO;
import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.UsuarioEntrarDTO;
import io.swagger.annotations.Api;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Api
@Local
@Path("noticias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface NoticiaResource {

    @GET
    @Path("/{id}")
    Response findById(@PathParam("id") Long id);

    @GET
    Response findAll();

    @POST
    @Path("/cadastrar")
    Response cadastrar(NoticiaDTO noticiaDTO, @Context UriInfo uriInfo);

    @PUT
    @Path("/atualizar/{id}")
    Response atualizarNoticia(@PathParam("id") Long id, NoticiaDTO noticiaDTO, @Context UriInfo uriInfo);

    @DELETE
    @Path("/deletar/{id}")
    Response deletar(@PathParam("id") Long id);
}
