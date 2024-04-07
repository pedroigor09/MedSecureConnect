package pedroigor.API_MedVoll.controller.Medical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pedroigor.API_MedVoll.controller.Medical.Endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medical {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medical(CadastroMedicos cadastroMedicos) {
        this.ativo = true;
        this.nome = cadastroMedicos.nome();
        this.email = cadastroMedicos.email();
        this.telefone = cadastroMedicos.telefone();
        this.crm = cadastroMedicos.crm();
        this.especialidade = cadastroMedicos.especialidade();
        this.endereco = new Endereco(cadastroMedicos.endereco());
    }

    public void AtualizarInfo(AtualizarMedicos atualizarMedicos) {
        if (atualizarMedicos.nome() != null){
            this.nome = atualizarMedicos.nome();
        }
        if (atualizarMedicos.telefone() != null){
            this.telefone = atualizarMedicos.telefone();
        }
        if (atualizarMedicos.endereco() != null){
            this.endereco.atualizarEndereco(atualizarMedicos.endereco());
        }
    }

    public void ativo() {
        this.ativo = false;
    }
}
