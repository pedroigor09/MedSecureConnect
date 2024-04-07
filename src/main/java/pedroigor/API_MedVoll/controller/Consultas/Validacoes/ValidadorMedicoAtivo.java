package pedroigor.API_MedVoll.controller.Consultas.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pedroigor.API_MedVoll.controller.Consultas.DadosAgendamentoConsultas;
import pedroigor.API_MedVoll.controller.Medical.MedicalRepository;
import pedroigor.API_MedVoll.controller.ValidacaoException;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private MedicalRepository medicalRepository;

    public void validar(DadosAgendamentoConsultas dadosAgendamentoConsultas){
        if (dadosAgendamentoConsultas.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = medicalRepository.findAtivoById(dadosAgendamentoConsultas.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico");
        }
    }
}
