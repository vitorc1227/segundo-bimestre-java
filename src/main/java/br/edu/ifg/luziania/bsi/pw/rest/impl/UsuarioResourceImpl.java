package br.edu.ifg.luziania.bsi.pw.rest.impl;

import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.UsuarioCadastrarDTO;
import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.UsuarioEntrarDTO;
import br.edu.ifg.luziania.bsi.pw.rest.UsuarioResource;
import br.edu.ifg.luziania.bsi.pw.service.UsuarioService;
import br.edu.ifg.luziania.bsi.pw.service.impl.UsuarioServiceImpl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class UsuarioResourceImpl implements UsuarioResource {

    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    public Response findById(Long id) {
        return usuarioService.buscarPorId(id);
    }

    @Override
    public Response findAll() {
        return usuarioService.todosUsuarios();
    }

    public Response entrar(UsuarioEntrarDTO usuarioEntrarDTO) {
        return usuarioService.entrar(usuarioEntrarDTO);
    }

    public Response cadastrar(UsuarioCadastrarDTO usuarioCadastrarDTO, UriInfo uriInfo) {
        return usuarioService.cadastrar(usuarioCadastrarDTO, uriInfo);
    }

    @Override
    public Response atualizarCargo(String email) {
        return usuarioService.atualizarCargo(email);
    }

    @Override
    public Response deletar(String email) {
        return usuarioService.deletar(email);
    }

}
