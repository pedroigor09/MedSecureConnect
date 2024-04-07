package pedroigor.API_MedVoll.controller.Medical.Endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.cep = endereco.cep();
    }

    public void atualizarEndereco(Endereco endereco) {
        if (endereco.logradouro != null){
            this.logradouro = endereco.getLogradouro();
        }
        if (endereco.numero != null){
            this.numero = endereco.getNumero();
        }
        if (endereco.complemento != null){
            this.complemento = endereco.getComplemento();
        }
        if (endereco.bairro != null){
            this.bairro = endereco.getBairro();
        }
        if (endereco.cidade != null){
            this.cidade = endereco.getCidade();
        }
        if (endereco.uf != null){
            this.uf = endereco.getUf();
        }
        if (endereco.cep != null){
            this.cep = endereco.getCep();
        }
    }
}
