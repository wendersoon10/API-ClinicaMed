package med.clinica.api.controller;

import jakarta.validation.Valid;
import med.clinica.api.domain.consulta.AgendaDeConsultas;
import med.clinica.api.domain.consulta.DadosAgendamentoConsulta;
import med.clinica.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private AgendaDeConsultas agenda;

    public ConsultaController(AgendaDeConsultas agenda) {
        this.agenda = agenda;
    }

    @PostMapping
    @Transactional
    public ResponseEntity agendar (@RequestBody @Valid DadosAgendamentoConsulta dados){
         var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar (@RequestBody @Valid DadosCancelamentoConsulta dados){
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
