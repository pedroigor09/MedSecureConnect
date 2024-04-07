package pedroigor.API_MedVoll.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pedroigor.API_MedVoll.controller.Paciente.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity Cadastro(@RequestBody @Valid CadastroClientes cadastroClientes, UriComponentsBuilder uriComponentsBuilder){
        var paciente = new Paciente(cadastroClientes);
        pacienteRepository.save(paciente);

        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacientes>> list(@PageableDefault(size = 30) Pageable pageable){
       var page = pacienteRepository.findAllByAtivoTrue(pageable).map(ListagemPacientes::new);

       return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarPaciente atualizarPaciente){
        var paciente = pacienteRepository.getReferenceById(atualizarPaciente.id());
        paciente.atualizarInfoPaciente(atualizarPaciente);

        return ResponseEntity.ok().body(new DetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalhar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhamentoPaciente(paciente));
    }
}
