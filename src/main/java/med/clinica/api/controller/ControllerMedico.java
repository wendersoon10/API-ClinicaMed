package med.clinica.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.clinica.api.endereco.Endereco;
import med.clinica.api.medicos.DadosCadastroMedico;
import med.clinica.api.medicos.Medico;
import med.clinica.api.medicos.MedicoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class ControllerMedico {


    private MedicoRepository repository;

    public ControllerMedico(MedicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }
}
