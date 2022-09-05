package br.edu.ifg.luziania.bsi.pw.service;


import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.UsuarioCadastrarDTO;
import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.UsuarioEntrarDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public interface UsuarioService {

    void criarTabela();

    Response cadastrar(UsuarioCadastrarDTO usuarioCadastrarDTO, UriInfo uriInfo);


    Response entrar(UsuarioEntrarDTO usuarioEntrarDTO);

    Response buscarPorId(Long id);

    Response todosUsuarios();

    Response atualizarCargo(String email);

    Response deletar(String email);
}
