package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Medico;
import com.example.demo.Service.IMedicoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final IMedicoService medicoService;

    public MedicoController(IMedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        try {
            List<Medico> autores = medicoService.listarMedicos();
            if (autores.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 — éxito pero sin contenido
            }
            return ResponseEntity.ok(autores); // 200 — éxito con la lista
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedicoPorId(@PathVariable Long id) {
        try {
            return medicoService.obtenerMedicoPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 — recurso no encontrado
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarMedico(@Valid @RequestBody Medico medico) {
        try {
            medicoService.agregarMedico(medico);
            return ResponseEntity.status(HttpStatus.CREATED).body("Médico agregado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400 — solicitud incorrecta
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar el médico"); // 500
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable Long id,
            @Valid @RequestBody Medico medico) {
        try {
            Medico actualizado = medicoService.actualizarMedico(id, medico);
            if (actualizado == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMedico(@PathVariable Long id) {
        try {
            boolean eliminado = medicoService.eliminarMedico(id);
            if (eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontrí ningún autor con id " + id);
            }
            return ResponseEntity.ok("Medico con id " + id + " eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el médico"); // 500
        }
    }

    @GetMapping("/total")
    public ResponseEntity<String> contarMedicos() {
        try {
            int total = medicoService.contarMedicos();
            return ResponseEntity.ok("Total de médicos: " + total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
