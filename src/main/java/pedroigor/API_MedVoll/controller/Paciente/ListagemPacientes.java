package pedroigor.API_MedVoll.controller.Paciente;

public record ListagemPacientes(
        Long id,
        String nome,
        String email,
        String cpf) {

    public ListagemPacientes(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
