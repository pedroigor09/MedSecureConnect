package pedroigor.API_MedVoll.controller.Paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import pedroigor.API_MedVoll.controller.Medical.Endereco.DadosEndereco;
import pedroigor.API_MedVoll.controller.Medical.Endereco.Endereco;

public record CadastroClientes(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{9}")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        DadosEndereco endereco) {
}
