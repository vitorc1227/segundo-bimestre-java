package br.edu.ifg.luziania.bsi.pw.model.entities.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntrarDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private Short cargo;

    private String senha;
}
