package med.clinica.api.domain.consulta.validacoes;

import med.clinica.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsultas {

    void validar(DadosAgendamentoConsulta dados);
}
