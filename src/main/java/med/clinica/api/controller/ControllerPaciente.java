package med.clinica.api.controller;

import jakarta.validation.Valid;
import med.clinica.api.pacientes.DadosCadastroPaciente;
import med.clinica.api.pacientes.DadosListagemPaciente;
import med.clinica.api.pacientes.Paciente;
import med.clinica.api.pacientes.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pacientes")
public class ControllerPaciente {

    private PacienteRepository repository;

    public ControllerPaciente(PacienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void cadastro(@RequestBody @Valid DadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
}
