package med.clinica.api.pacientes;

import jakarta.validation.constraints.NotNull;
import med.clinica.api.endereco.DadosEndereco;


public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,

        String nome,

        String telefone,

        DadosEndereco endereco) {

}
