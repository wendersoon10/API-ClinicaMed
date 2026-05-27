package med.clinica.api.domain.consulta;

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
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = medicoRepository.findById(dados.idMedico()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }
}