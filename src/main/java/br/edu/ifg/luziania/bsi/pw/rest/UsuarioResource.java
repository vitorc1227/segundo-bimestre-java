package br.edu.ifg.luziania.bsi.pw.rest;

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
@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UsuarioResource {


    @GET
    @Path("/{id}")
    Response findById(@PathParam("id") Long id);

    @GET
    Response findAll();

    @POST
    @Path("/entrar")
    Response entrar(UsuarioEntrarDTO usuarioEntrarDTO);

    @POST
    @Path("/cadastrar")
    Response cadastrar(UsuarioCadastrarDTO usuarioCadastrarDTO, @Context UriInfo uriInfo);

    @PUT
    @Path("/atualizar/cargo/{email}")
    Response atualizarCargo(@PathParam("email") String email);


    @DELETE
    @Path("/deletar/{email}")
    Response deletar(@PathParam("email") String email);

}
