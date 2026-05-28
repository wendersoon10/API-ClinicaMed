package med.clinica.api.domain.consulta.validacoes;

import med.clinica.api.domain.ValidacaoException;
import med.clinica.api.domain.consulta.ConsultaRepository;
import med.clinica.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComHorarioMarcado implements ValidadorAgendamentoDeConsultas {

    private ConsultaRepository repository;

    public ValidadorMedicoComHorarioMarcado(ConsultaRepository repository) {
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsulta dados){
        var medicoIndisponivel = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoIndisponivel){
            throw new ValidacaoException("Médico já possui outra consulta no mesmo horário");
        }
    }
}
