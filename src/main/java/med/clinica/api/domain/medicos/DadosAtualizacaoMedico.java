package med.clinica.api.domain.medicos;

import jakarta.validation.constraints.NotNull;
import med.clinica.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,

        String telefone,

        String nome,

        DadosEndereco endereco) {
}
