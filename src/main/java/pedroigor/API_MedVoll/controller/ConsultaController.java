package pedroigor.API_MedVoll.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedroigor.API_MedVoll.controller.Consultas.AgendaDeConsultas;
import pedroigor.API_MedVoll.controller.Consultas.DadosAgendamentoConsultas;
import pedroigor.API_MedVoll.controller.Consultas.DadosDetalhamentoConsulta;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultas dadosAgendamentoConsultas){
        var dto = agendaDeConsultas.agendar(dadosAgendamentoConsultas);
        return ResponseEntity.ok(dto);
    }
}
