package med.clinica.api.domain.consulta.validacoes.agendamento;

import med.clinica.api.domain.ValidacaoException;
import med.clinica.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoDeConsultas {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var preExpediente = dataConsulta.getHour() < 7;
        var posExpediente = dataConsulta.getHour() > 18;
        if(domingo || preExpediente || posExpediente){
            throw new ValidacaoException("Consulta fora do horário de funcionamento");
        }
    }
}
