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
import pedroigor.API_MedVoll.controller.Medical.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicalController {

    @Autowired
    private MedicalRepository medicalRepository;

    @PostMapping
    @Transactional
    public ResponseEntity Cadastro(@RequestBody @Valid CadastroMedicos cadastroMedicos, UriComponentsBuilder uriComponentsBuilder){
        var medical = new Medical(cadastroMedicos);
        medicalRepository.save(medical);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medical).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoMedico(medical));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicos>> list(@PageableDefault(sort = {"nome"}, size = 30) Pageable pageable){
       var page = medicalRepository.findAllByAtivoTrue(pageable).map(ListagemMedicos::new);

       return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity Atualizar(@RequestBody @Valid AtualizarMedicos atualizarMedicos){
        var medical = medicalRepository.getReferenceById(atualizarMedicos.id());
        medical.AtualizarInfo(atualizarMedicos);

        return ResponseEntity.ok().body(new DetalhamentoMedico(medical));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity Deletar(@PathVariable Long id){
        var medical = medicalRepository.getReferenceById(id);
        medical.ativo();

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity Detalhar(@PathVariable Long id){
        var medical = medicalRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhamentoMedico(medical));
    }
}
