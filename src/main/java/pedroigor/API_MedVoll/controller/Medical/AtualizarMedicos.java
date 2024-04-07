package pedroigor.API_MedVoll.controller.Medical;

import jakarta.validation.constraints.NotNull;
import pedroigor.API_MedVoll.controller.Medical.Endereco.Endereco;

public record AtualizarMedicos(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco) {
}
