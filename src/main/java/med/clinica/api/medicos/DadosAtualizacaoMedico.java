package med.clinica.api.medicos;

import jakarta.validation.constraints.NotNull;
import med.clinica.api.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,

        String telefone,

        String nome,

        DadosEndereco endereco) {
}
