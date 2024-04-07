package pedroigor.API_MedVoll.controller.Consultas.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pedroigor.API_MedVoll.controller.Consultas.ConsultaRepository;
import pedroigor.API_MedVoll.controller.Consultas.DadosAgendamentoConsultas;
import pedroigor.API_MedVoll.controller.Paciente.PacienteRepository;
import pedroigor.API_MedVoll.controller.ValidacaoException;
@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsultas dadosAgendamentoConsultas){
        var primeiroHorario = dadosAgendamentoConsultas.data().withHour(7);
        var ultimoHorario = dadosAgendamentoConsultas.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dadosAgendamentoConsultas.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
