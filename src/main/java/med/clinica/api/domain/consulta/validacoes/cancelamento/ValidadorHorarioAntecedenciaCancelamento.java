package med.clinica.api.domain.consulta.validacoes.cancelamento;

import med.clinica.api.domain.ValidacaoException;
import med.clinica.api.domain.consulta.ConsultaRepository;
import med.clinica.api.domain.consulta.DadosCancelamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedenciaCancelamento implements ValidadorCancelamentoDeConsulta{

    private ConsultaRepository repository;

    public ValidadorHorarioAntecedenciaCancelamento(ConsultaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }

    }
}
