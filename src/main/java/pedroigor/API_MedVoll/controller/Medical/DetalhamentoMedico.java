package pedroigor.API_MedVoll.controller.Medical;

import pedroigor.API_MedVoll.controller.Medical.Endereco.Endereco;

public record DetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidadee,
        Endereco endereco) {

    public DetalhamentoMedico(Medical medical){
        this(medical.getId(), medical.getNome(), medical.getEmail(), medical.getTelefone(), medical.getCrm(), medical.getEspecialidade(), medical.getEndereco());
    }
}
