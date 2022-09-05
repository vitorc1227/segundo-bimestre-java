package br.edu.ifg.luziania.bsi.pw.service.impl;

import br.edu.ifg.luziania.bsi.pw.model.dao.UsuarioDao;
import br.edu.ifg.luziania.bsi.pw.model.dao.impl.UsuarioDaoImpl;
import br.edu.ifg.luziania.bsi.pw.model.entities.Usuario;
import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.UsuarioCadastrarDTO;
import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.UsuarioEntrarDTO;
import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.UsuarioRespostaDTO;
import br.edu.ifg.luziania.bsi.pw.service.UsuarioService;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao usuarioDao = new UsuarioDaoImpl();

    @Override
    public void criarTabela() {
        usuarioDao.createTable();
    }


    @Override
    public Response cadastrar(UsuarioCadastrarDTO usuarioCadastrarDTO, UriInfo uriInfo) {
        criarTabela();
        Usuario usuario = Usuario.builder()
                .email(usuarioCadastrarDTO.getEmail())
                .nome(usuarioCadastrarDTO.getNome())
                .senha(usuarioCadastrarDTO.getSenha())
                .cargo(usuarioCadastrarDTO.getCargo() != null ? usuarioCadastrarDTO.getCargo() : 0)
                .build();
        boolean usuarioOk = verificaCampos(usuario);
        if (usuarioOk) {
            Usuario usuarioFinal = usuarioDao.insert(usuario);
            if (verificaUsuario(usuarioFinal)) {
                UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                uriBuilder.path(Long.toString(usuario.getId()));
                return Response.created(uriBuilder.build())
                        .entity(UsuarioRespostaDTO.builder()
                                .email(usuarioFinal.getEmail())
                                .nome(usuarioFinal.getNome())
                                .cargo(usuarioFinal.getCargo())
                                .build())
                        .build();
            }
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    private boolean verificaCampos(Usuario usuario) {
        if (usuario.getNome().equals(""))
            return false;

        if (usuario.getEmail().equals(""))
            return false;

        if (usuario.getSenha().equals(""))
            return false;

        return true;
    }

    @Override
    public Response entrar(UsuarioEntrarDTO usuarioEntrarDTO) {
        criarTabela();
        Usuario usuario = Usuario.builder()
                .email(usuarioEntrarDTO.getEmail())
                .senha(usuarioEntrarDTO.getSenha())
                .build();
        Usuario usuarioFinal = (Usuario) usuarioDao.existByEmailAndSenha(usuario);

        if (verificaUsuario(usuarioFinal)) {
            return Response.ok(UsuarioRespostaDTO.builder()
                    .email(usuarioFinal.getEmail())
                    .nome(usuarioFinal.getNome())
                    .cargo(usuarioFinal.getCargo())
                    .build()).build();
        }

        return Response
                .status(Response.Status.UNAUTHORIZED)
                .build();
    }

    @Override
    public Response buscarPorId(Long id) {
        Usuario usuario = usuarioDao.findById(id);
        if (verificaUsuario(usuario)) {
            return Response.ok(UsuarioRespostaDTO.builder()
                    .email(usuario.getEmail())
                    .nome(usuario.getNome())
                    .cargo(usuario.getCargo())
                    .build()).build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @Override
    public Response todosUsuarios() {
        List<Usuario> usuario = usuarioDao.findAll();
        List<UsuarioRespostaDTO> usuarioDTO = new ArrayList<>();

        usuario.forEach(u -> {
            UsuarioRespostaDTO usuarioRespostaDTO = UsuarioRespostaDTO.builder()
                    .email(u.getEmail())
                    .nome(u.getNome())
                    .cargo(u.getCargo())
                    .build();
            usuarioDTO.add(usuarioRespostaDTO);
        });

        return Response.ok(usuarioDTO).build();
    }

    @Override
    public Response atualizarCargo(String email) {
        Usuario usuario = usuarioDao.findByEmail(email);

        short role;
        if (usuario.getCargo() == 0) {
            role = 1;
        } else if (usuario.getCargo() == 1) {
            role = 2;
        } else if (usuario.getCargo() == 2) {
            role = 0;
        } else {
            role = -1;
        }

        int linhasAfetadas = usuarioDao.updateRole(role, usuario.getId());

        if (linhasAfetadas > 0) {
            return Response.ok().build();
        }

        return Response.notModified().build();
    }

    @Override
    public Response deletar(String email) {
        int linhasAfetadas = usuarioDao.deleteByEmail(email);

        if (linhasAfetadas > 0) {
            return Response.ok().build();
        }

        return Response.notModified().build();
    }

    public boolean verificaUsuario(Usuario usuario) {
        return usuario.getId() != null && usuario.getNome() != null;
    }
}
