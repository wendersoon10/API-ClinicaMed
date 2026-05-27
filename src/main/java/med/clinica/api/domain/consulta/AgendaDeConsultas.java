package med.clinica.api.domain.consulta;

import med.clinica.api.domain.ValidacaoException;
import med.clinica.api.domain.medicos.Medico;
import med.clinica.api.domain.medicos.MedicoRepository;
import med.clinica.api.domain.pacientes.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    private ConsultaRepository consultaRepository;
    private PacienteRepository pacienteRepository;
    private MedicoRepository medicoRepository;

    public AgendaDeConsultas(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public void agendar(DadosAgendamentoConsulta dados)
    {

        if (dados.idMedico() != null && !pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente não existe!");
        }

        if (!medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico não existe!");
        }
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioDataLivre(dados.especialidade(), dados.especialidade());
    }
}