package pedroigor.API_MedVoll.controller.Consultas.Validacoes;

import org.springframework.stereotype.Component;
import pedroigor.API_MedVoll.controller.Consultas.DadosAgendamentoConsultas;
import pedroigor.API_MedVoll.controller.ValidacaoException;

import java.time.DayOfWeek;

@Component
public class ValidarFuncionamentoClinica implements ValidadorAgendamentoDeConsultas{

    public void validar(DadosAgendamentoConsultas dadosAgendamentoConsultas){
        var dataConsulta = dadosAgendamentoConsultas.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Tentativa de consulta fora do horário de expediente!");
        }
    }
}
