package pedroigor.API_MedVoll.controller.Paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pedroigor.API_MedVoll.controller.Medical.Endereco.DadosEndereco;
import pedroigor.API_MedVoll.controller.Medical.Endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(CadastroClientes cadastroClientes) {
        this.ativo = true;
        this.nome = cadastroClientes.nome();
        this.email = cadastroClientes.email();
        this.telefone = cadastroClientes.telefone();
        this.cpf = cadastroClientes.cpf();
        this.endereco = new Endereco(cadastroClientes.endereco());
    }

    public void atualizarInfoPaciente(AtualizarPaciente atualizarPaciente) {
        if (atualizarPaciente.nome() != null) {
            this.nome = atualizarPaciente.nome();
        }
        if (atualizarPaciente.telefone() != null) {
            this.telefone = atualizarPaciente.telefone();
        }
        if (atualizarPaciente.endereco() != null) {
            this.endereco.atualizarEndereco(atualizarPaciente.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
