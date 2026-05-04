package med.clinica.api.medicos;

import med.clinica.api.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String telefone, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
