package med.clinica.api.domain.validacoes;

import med.clinica.api.domain.ValidacaoException;
import med.clinica.api.domain.consulta.ConsultaRepository;
import med.clinica.api.domain.consulta.DadosAgendamentoConsulta;
import med.clinica.api.domain.medicos.MedicoRepository;

public class ValidadorMedicoComHorarioMarcado {

    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoIndisponivel = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoIndisponivel){
            throw new ValidacaoException("Médico já possui outra consulta no mesmo horário");
        }
    }
}
