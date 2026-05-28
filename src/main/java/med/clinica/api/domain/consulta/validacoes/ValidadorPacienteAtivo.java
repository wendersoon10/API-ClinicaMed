package med.clinica.api.domain.consulta.validacoes;

import med.clinica.api.domain.ValidacaoException;
import med.clinica.api.domain.consulta.DadosAgendamentoConsulta;
import med.clinica.api.domain.pacientes.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {

    private PacienteRepository repository;

    public ValidadorPacienteAtivo(PacienteRepository repository) {
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo");
        }
    }
}
