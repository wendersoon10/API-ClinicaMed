package med.clinica.api.domain.consulta.validacoes.agendamento;

import med.clinica.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsultas {

    void validar(DadosAgendamentoConsulta dados);
}
