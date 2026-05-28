package med.clinica.api.domain.consulta;

import jakarta.validation.Valid;
import med.clinica.api.domain.ValidacaoException;
import med.clinica.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsultas;
import med.clinica.api.domain.medicos.Medico;
import med.clinica.api.domain.medicos.MedicoRepository;
import med.clinica.api.domain.pacientes.PacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgendaDeConsultas {

    private ConsultaRepository consultaRepository;
    private PacienteRepository pacienteRepository;
    private MedicoRepository medicoRepository;

    private List<ValidadorAgendamentoDeConsultas> validadores;

    public AgendaDeConsultas(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository, List<ValidadorAgendamentoDeConsultas> validadores) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.validadores = validadores;
    }

    public void agendar(DadosAgendamentoConsulta dados)
    {

        if (dados.idMedico() != null && !pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente não existe!");
        }

        if (!medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

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

    public void cancelar(@Valid DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}