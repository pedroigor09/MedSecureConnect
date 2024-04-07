package pedroigor.API_MedVoll.controller.Paciente;

import jakarta.validation.constraints.NotNull;
import pedroigor.API_MedVoll.controller.Medical.Endereco.Endereco;

public record AtualizarPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco) {
}
