package br.edu.ifg.luziania.bsi.pw.model.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Edital implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String descricao;
}
