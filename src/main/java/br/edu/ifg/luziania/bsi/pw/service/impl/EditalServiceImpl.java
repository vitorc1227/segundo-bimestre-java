package br.edu.ifg.luziania.bsi.pw.service.impl;

import br.edu.ifg.luziania.bsi.pw.model.dao.EditalDao;
import br.edu.ifg.luziania.bsi.pw.model.dao.impl.EditalDaoImpl;
import br.edu.ifg.luziania.bsi.pw.model.entities.Edital;
import br.edu.ifg.luziania.bsi.pw.model.entities.dtos.EditalDTO;
import br.edu.ifg.luziania.bsi.pw.service.EditalService;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

public class EditalServiceImpl implements EditalService {

    private final EditalDao editalDao = new EditalDaoImpl();

    @Override
    public void criarTabela() {
        editalDao.createTable();
    }


    @Override
    public Response cadastrar(EditalDTO usuarioCadastrarDTO, UriInfo uriInfo) {
        criarTabela();
        Edital edital = Edital.builder()
                .nome(usuarioCadastrarDTO.getNome())
                .descricao(usuarioCadastrarDTO.getDescricao())
                .build();
        boolean editalOk = verificaCampos(edital);
        if (editalOk) {
            Edital editalFinal = editalDao.insert(edital);
            if (verificaEdital(editalFinal)) {
                UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                uriBuilder.path(Long.toString(edital.getId()));
                return Response.created(uriBuilder.build())
                        .entity(EditalDTO.builder()
                                .nome(editalFinal.getNome())
                                .descricao(editalFinal.getDescricao())
                                .build())
                        .build();
            }
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    private boolean verificaCampos(Edital edital) {
        if (edital.getNome().equals(""))
            return false;

        if (edital.getDescricao().equals(""))
            return false;

        return true;
    }

    @Override
    public Response buscarPorId(Long id) {
        Edital edital = editalDao.findById(id);
        if (verificaEdital(edital)) {
            return Response.ok(EditalDTO.builder()
                    .nome(edital.getNome())
                    .descricao(edital.getDescricao())
                    .build()).build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @Override
    public Response findAll() {
        List<Edital> edital = editalDao.findAll();
        List<EditalDTO> editalDTO = new ArrayList<>();

        edital.forEach(n -> {
            EditalDTO editalFinal = EditalDTO.builder()
                    .id(n.getId())
                    .nome(n.getNome())
                    .descricao(n.getDescricao())
                    .build();
            editalDTO.add(editalFinal);
        });

        return Response.ok(editalDTO).build();
    }

    @Override
    public Response atualizarEdital(Long id, EditalDTO editalDTO, UriInfo uriInfo) {
        Edital edital = editalDao.findById(id);
        if (edital != null) {
            if (editalDTO.getNome() != null && !(edital.getNome().equals(editalDTO.getNome())))
                edital.setNome(editalDTO.getNome());

            if (editalDTO.getDescricao() != null && !(edital.getDescricao().equals(editalDTO.getDescricao())))
                edital.setDescricao(editalDTO.getDescricao());

            int linhasAfetadas = editalDao.update(edital);

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
        int linhasAfetadas = editalDao.deleteById(id);

        if (linhasAfetadas > 0) {
            return Response.ok().build();
        }

        return Response.notModified().build();
    }

    public boolean verificaEdital(Edital edital) {
        return edital.getId() != null && edital.getNome() != null;
    }
}
