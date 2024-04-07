package pedroigor.API_MedVoll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import pedroigor.API_MedVoll.controller.Consultas.Consulta;
import pedroigor.API_MedVoll.controller.Medical.CadastroMedicos;
import pedroigor.API_MedVoll.controller.Medical.Endereco.DadosEndereco;
import pedroigor.API_MedVoll.controller.Medical.Especialidade;
import pedroigor.API_MedVoll.controller.Medical.Medical;
import pedroigor.API_MedVoll.controller.Medical.MedicalRepository;
import pedroigor.API_MedVoll.controller.Paciente.CadastroClientes;
import pedroigor.API_MedVoll.controller.Paciente.Paciente;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MedicalRepositoryTest {

    @Autowired
    private MedicalRepository medicalRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver NULL quando o unico medico cadastrado não está disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1(){
        //Given ou arrange
        var proximaSegundaAs10 = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)).toLocalDate()
                .atTime(10,0);


        var medical = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "29384928191");
        cadastrarConsulta(medical, paciente, proximaSegundaAs10);

        //When ou act
        var medicoLivre = medicalRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //Then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver MEDICO quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2(){
        //Given ou arrange
        var proximaSegundaAs10 = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)).toLocalDate()
                .atTime(10,0);

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        //When ou act
        var medicoLivre = medicalRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //Then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medical medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private Medical cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medical(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private CadastroMedicos dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new CadastroMedicos(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private CadastroClientes dadosPaciente(String nome, String email, String cpf) {
        return new CadastroClientes(
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
                "42721-810"
        );
    }
}
