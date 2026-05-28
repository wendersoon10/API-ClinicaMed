package med.clinica.api.domain.validacoes;

import med.clinica.api.domain.ValidacaoException;
import med.clinica.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDate;

public class ValidadorHorarioAntecedencia {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDate.now();
        var diferencaMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
