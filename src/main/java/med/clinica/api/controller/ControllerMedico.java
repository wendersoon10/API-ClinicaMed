package med.clinica.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.clinica.api.medicos.DadosCadastroMedico;
import med.clinica.api.medicos.DadosListagemMedico;
import med.clinica.api.medicos.Medico;
import med.clinica.api.medicos.MedicoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<DadosListagemMedico> listar(){
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
