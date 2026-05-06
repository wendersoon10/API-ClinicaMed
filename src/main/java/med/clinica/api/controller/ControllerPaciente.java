package med.clinica.api.controller;

import jakarta.validation.Valid;
import med.clinica.api.pacientes.DadosCadastroPaciente;
import med.clinica.api.pacientes.Paciente;
import med.clinica.api.pacientes.PacienteRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
