package pedroigor.API_MedVoll.controller.Consultas.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pedroigor.API_MedVoll.controller.Consultas.DadosAgendamentoConsultas;
import pedroigor.API_MedVoll.controller.Paciente.PacienteRepository;
import pedroigor.API_MedVoll.controller.ValidacaoException;
@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsultas dadosAgendamentoConsultas){
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dadosAgendamentoConsultas.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
