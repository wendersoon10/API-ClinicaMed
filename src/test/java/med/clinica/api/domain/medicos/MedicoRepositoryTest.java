package med.clinica.api.domain.medicos;

import med.clinica.api.domain.consulta.Consulta;
import med.clinica.api.domain.endereco.DadosEndereco;
import med.clinica.api.domain.pacientes.DadosCadastroPaciente;
import med.clinica.api.domain.pacientes.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deveria devolver null quando o único médico cadastrado não está disponível na data")
    void escolherMedicoAleatorioDataLivreCenario1() {
        var proximaQuartaAs14 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY))
                .atTime(14,0);


        var medico = cadastrarMedico("Medico", "medico@clin.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@clin.med", "00000000000");
        cadastrarConsulta(medico, paciente, proximaQuartaAs14);


        var medicoLivre = medicoRepository.escolherMedicoAleatorioDataLivre(Especialidade.CARDIOLOGIA, proximaQuartaAs14);
        assertThat(medicoLivre).isNull();

    }

    @Test
    @DisplayName("Deveria devolver médico quando ele estiver disponível na data")
    void escolherMedicoAleatorioDataLivreCenario2() {
        var proximaQuartaAs14 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY))
                .atTime(14,0);


       var medico = cadastrarMedico("Medico", "medico@clin.med", "123456", Especialidade.CARDIOLOGIA);



        var medicoLivre = medicoRepository.escolherMedicoAleatorioDataLivre(Especialidade.CARDIOLOGIA, proximaQuartaAs14);
        assertThat(medicoLivre).isEqualTo(medico);

    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        testEntityManager.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        testEntityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        testEntityManager.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}