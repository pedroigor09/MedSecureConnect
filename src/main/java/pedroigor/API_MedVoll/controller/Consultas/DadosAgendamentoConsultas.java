package pedroigor.API_MedVoll.controller.Consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import pedroigor.API_MedVoll.controller.Medical.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsultas(
        Long idMedico,
        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade) {
}
