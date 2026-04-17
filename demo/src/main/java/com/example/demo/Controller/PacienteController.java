package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Paciente;
import com.example.demo.Service.IPacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        try {
            List<Paciente> pacientes = pacienteService.listarPacientes();

            if (pacientes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(pacientes);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPacientePorId(@PathVariable Long id) {
        try {
            return pacienteService.obtenerPacientePorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarPaciente(@Valid @RequestBody Paciente paciente) {
        try {
            pacienteService.agregarPaciente(paciente);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Paciente agregado exitosamente");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el paciente");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(
            @PathVariable Long id,
            @Valid @RequestBody Paciente paciente) {

        try {
            Paciente actualizado = pacienteService.actualizarPaciente(id, paciente);

            if (actualizado == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(actualizado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {

        try {
            boolean eliminado = pacienteService.eliminarPaciente(id);

            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró ningún paciente con id " + id);
            }

            return ResponseEntity.ok("Paciente con id " + id + " eliminado exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el paciente");
        }
    }

    @GetMapping("/total")
    public ResponseEntity<String> contarPacientes() {

        try {
            int total = pacienteService.contarPacientes();

            return ResponseEntity.ok("Total de pacientes: " + total);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
