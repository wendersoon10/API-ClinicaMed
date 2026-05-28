package med.clinica.api.domain.consulta.validacoes;

import med.clinica.api.domain.ValidacaoException;
import med.clinica.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsultas {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDate.now();
        var diferencaMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
