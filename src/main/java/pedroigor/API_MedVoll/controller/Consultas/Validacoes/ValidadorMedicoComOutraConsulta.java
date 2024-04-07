package pedroigor.API_MedVoll.controller.Consultas.Validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pedroigor.API_MedVoll.controller.Consultas.ConsultaRepository;
import pedroigor.API_MedVoll.controller.Consultas.DadosAgendamentoConsultas;
import pedroigor.API_MedVoll.controller.ValidacaoException;

@Component
public class ValidadorMedicoComOutraConsulta implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsultas dadosAgendamentoConsultas){
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dadosAgendamentoConsultas.idMedico(), dadosAgendamentoConsultas.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Medico já possui outra consulta agendada nesse mesmo horário!");
        }
    }
}
