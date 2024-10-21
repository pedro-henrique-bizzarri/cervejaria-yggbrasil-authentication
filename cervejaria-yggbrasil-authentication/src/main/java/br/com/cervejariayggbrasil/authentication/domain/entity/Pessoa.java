package br.com.cervejariayggbrasil.authentication.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "pessoa", sequenceName = "sq_pessoa", allocationSize = 1, initialValue = 1)
public class Pessoa {

    @Id
    @Column(name="id_pesssoa")
    @GeneratedValue(generator="pessoa",strategy=GenerationType.SEQUENCE)
    private int id;

    private String nome;

    private String sobrenome;

    private int idade;

    private String cpf;

    private String endereco;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
