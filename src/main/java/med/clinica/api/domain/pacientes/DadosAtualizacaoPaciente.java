package med.clinica.api.domain.pacientes;

import jakarta.validation.constraints.NotNull;
import med.clinica.api.domain.endereco.DadosEndereco;


public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,

        String nome,

        String telefone,

        DadosEndereco endereco) {

}
