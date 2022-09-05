package br.edu.ifg.luziania.bsi.pw.model.entities.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRespostaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    private Short cargo;

    private String email;
}
