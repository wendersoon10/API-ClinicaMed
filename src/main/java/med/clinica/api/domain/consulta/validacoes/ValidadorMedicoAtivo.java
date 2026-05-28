package med.clinica.api.domain.consulta.validacoes;

import med.clinica.api.domain.ValidacaoException;
import med.clinica.api.domain.consulta.DadosAgendamentoConsulta;
import med.clinica.api.domain.medicos.MedicoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas {


    private MedicoRepository repository;

    public ValidadorMedicoAtivo(MedicoRepository repository) {
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        var medicoAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo" );
        }
    }
}
