package br.edu.ifg.luziania.bsi.pw.rest;

import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.EditalDTO;
import io.swagger.annotations.Api;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Api
@Local
@Path("editais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface EditalResource {

    @GET
    @Path("/{id}")
    Response findById(@PathParam("id") Long id);

    @GET
    Response findAll();

    @POST
    @Path("/cadastrar")
    Response cadastrar(EditalDTO editalDTO, @Context UriInfo uriInfo);

    @PUT
    @Path("/atualizar/{id}")
    Response atualizarEdital(@PathParam("id") Long id, EditalDTO editalDTO, @Context UriInfo uriInfo);

    @DELETE
    @Path("/deletar/{id}")
    Response deletar(@PathParam("id") Long id);
}
