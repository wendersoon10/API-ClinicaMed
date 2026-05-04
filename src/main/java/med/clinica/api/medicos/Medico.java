package med.clinica.api.medicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.clinica.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;

    @Enumerated
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;


    public Medico(DadosCadastroMedico dados) {
        this.nome = nome;
        this.email = email;
        this.crm = crm;
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
}
