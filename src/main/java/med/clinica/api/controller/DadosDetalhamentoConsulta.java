package med.clinica.api.controller;

import med.clinica.api.domain.consulta.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long id,

        Long idMedico,

        Long idPaciente,

        LocalDateTime data

        ) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
