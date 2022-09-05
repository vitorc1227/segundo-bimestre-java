package br.edu.ifg.luziania.bsi.pw.service.impl;

import br.edu.ifg.luziania.bsi.pw.model.dao.NoticiaDao;
import br.edu.ifg.luziania.bsi.pw.model.dao.impl.NoticiaDaoImpl;
import br.edu.ifg.luziania.bsi.pw.model.entities.Noticia;
import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.NoticiaDTO;
import br.edu.ifg.luziania.bsi.pw.service.NoticiaService;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

public class NoticiaServiceImpl implements NoticiaService {

    private final NoticiaDao noticiaDao = new NoticiaDaoImpl();

    @Override
    public void criarTabela() {
        noticiaDao.createTable();
    }


    @Override
    public Response cadastrar(NoticiaDTO usuarioCadastrarDTO, UriInfo uriInfo) {
        criarTabela();
        Noticia noticia = Noticia.builder()
                .nome(usuarioCadastrarDTO.getNome())
                .descricao(usuarioCadastrarDTO.getDescricao())
                .build();
        boolean noticiaOk = verificaCampos(noticia);
        if (noticiaOk) {
            Noticia noticiaFinal = noticiaDao.insert(noticia);
            if (verificaNoticia(noticiaFinal)) {
                UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                uriBuilder.path(Long.toString(noticia.getId()));
                return Response.created(uriBuilder.build())
                        .entity(NoticiaDTO.builder()
                                .nome(noticiaFinal.getNome())
                                .descricao(noticiaFinal.getDescricao())
                                .build())
                        .build();
            }
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    private boolean verificaCampos(Noticia noticia) {
        if (noticia.getNome().equals(""))
            return false;

        if (noticia.getDescricao().equals(""))
            return false;

        return true;
    }

    @Override
    public Response buscarPorId(Long id) {
        Noticia noticia = noticiaDao.findById(id);
        if (verificaNoticia(noticia)) {
            return Response.ok(NoticiaDTO.builder()
                    .nome(noticia.getNome())
                    .descricao(noticia.getDescricao())
                    .build()).build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @Override
    public Response todosNoticias() {
        List<Noticia> noticia = noticiaDao.findAll();
        List<NoticiaDTO> noticiaDTO = new ArrayList<>();

        noticia.forEach(n -> {
            NoticiaDTO noticiaFinal = NoticiaDTO.builder()
                    .id(n.getId())
                    .nome(n.getNome())
                    .descricao(n.getDescricao())
                    .build();
            noticiaDTO.add(noticiaFinal);
        });

        return Response.ok(noticiaDTO).build();
    }

    @Override
    public Response atualizarNoticia(Long id, NoticiaDTO noticiaDTO, UriInfo uriInfo) {
        Noticia noticia = noticiaDao.findById(id);
        if (noticia != null) {
            if (noticiaDTO.getNome() != null && !(noticia.getNome().equals(noticiaDTO.getNome())))
                noticia.setNome(noticiaDTO.getNome());

            if (noticiaDTO.getDescricao() != null && !(noticia.getDescricao().equals(noticiaDTO.getDescricao())))
                noticia.setDescricao(noticiaDTO.getDescricao());

            int linhasAfetadas = noticiaDao.update(noticia);

            if (linhasAfetadas > 0) {
                return Response.ok().build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }


        return Response.notModified().build();
    }

    @Override
    public Response deletar(Long id) {
        int linhasAfetadas = noticiaDao.deleteById(id);

        if (linhasAfetadas > 0) {
            return Response.ok().build();
        }

        return Response.notModified().build();
    }

    public boolean verificaNoticia(Noticia noticia) {
        return noticia.getId() != null && noticia.getNome() != null;
    }
}
