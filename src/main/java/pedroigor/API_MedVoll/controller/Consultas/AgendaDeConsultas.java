package pedroigor.API_MedVoll.controller.Consultas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedroigor.API_MedVoll.controller.Consultas.Validacoes.ValidadorAgendamentoDeConsultas;
import pedroigor.API_MedVoll.controller.Consultas.Validacoes.ValidadorHorarioAntecendencia;
import pedroigor.API_MedVoll.controller.Medical.Medical;
import pedroigor.API_MedVoll.controller.Medical.MedicalRepository;
import pedroigor.API_MedVoll.controller.Paciente.PacienteRepository;
import pedroigor.API_MedVoll.controller.ValidacaoException;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicalRepository medicalRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadorAgendamentoDeConsultas;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsultas dadosAgendamentoConsultas){
        if (!pacienteRepository.existsById(dadosAgendamentoConsultas.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        if (dadosAgendamentoConsultas.idMedico() != null && !medicalRepository.existsById(dadosAgendamentoConsultas.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadorAgendamentoDeConsultas.forEach(v -> v.validar(dadosAgendamentoConsultas));

        var paciente = pacienteRepository.getReferenceById(dadosAgendamentoConsultas.idPaciente());
        var medical = escolherMedico(dadosAgendamentoConsultas);
        if (medical == null) {
            throw new ValidacaoException("Não existe médico disponivel nesta data!");
        }

        var consulta = new Consulta(null, medical, paciente, dadosAgendamentoConsultas.data());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medical escolherMedico(DadosAgendamentoConsultas dadosAgendamentoConsultas) {
        if (dadosAgendamentoConsultas.idMedico() != null){
            return medicalRepository.getReferenceById(dadosAgendamentoConsultas.idMedico());
        }

        if (dadosAgendamentoConsultas.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicalRepository.escolherMedicoAleatorioLivreNaData(dadosAgendamentoConsultas.especialidade(), dadosAgendamentoConsultas.data());

    }

}
