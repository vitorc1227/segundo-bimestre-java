package br.edu.ifg.luziania.bsi.pw.rest;

import br.edu.ifg.luziania.bsi.pw.rest.impl.EditalResourceImpl;
import br.edu.ifg.luziania.bsi.pw.rest.impl.NoticiaResourceImpl;
import br.edu.ifg.luziania.bsi.pw.rest.impl.UsuarioResourceImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class ApplicationRest extends Application {

    private final Set<Object> singletons = new HashSet<>();

    public ApplicationRest() {
        singletons.addAll(Arrays.asList(new UsuarioResourceImpl(), new NoticiaResourceImpl(),
                new EditalResourceImpl()));

    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
