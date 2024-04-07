package pedroigor.API_MedVoll.controller.Consultas.Validacoes;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pedroigor.API_MedVoll.controller.Consultas.DadosAgendamentoConsultas;
import pedroigor.API_MedVoll.controller.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecendencia implements ValidadorAgendamentoDeConsultas {

    public void validar(DadosAgendamentoConsultas dadosAgendamentoConsultas){

        var dataConsulta = dadosAgendamentoConsultas.data();
        var agora = LocalDateTime.now();
        var diferencaMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaMinutos < 30) {
            throw new ValidacaoException("O agendamento deve ser feita antes de 30 minutos de antecedência");
        }

    }
}
