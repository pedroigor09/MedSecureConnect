package pedroigor.API_MedVoll.controller.Medical;

import jakarta.validation.constraints.NotNull;

public record ListagemMedicos(
        @NotNull
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public ListagemMedicos(Medical medical){
        this(medical.getId(), medical.getNome(), medical.getEmail(), medical.getCrm(), medical.getEspecialidade());
    }
}
