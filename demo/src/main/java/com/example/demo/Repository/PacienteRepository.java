package com.example.demo.Repository;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PacienteRepository { 

    private final List<Paciente> pacientes = new ArrayList<>();

    public PacienteRepository() {

        pacientes.add(new Paciente(1L, "Juan", "Perez", 25, "999111222"));
        pacientes.add(new Paciente(2L, "Maria", "Lopez", 30, "999333444"));
        pacientes.add(new Paciente(3L, "Carlos", "Torres", 40, "999555666"));
        
    }

    // Listar todos
    public List<Paciente> findAll() {
        return pacientes;
    }

    // Buscar por ID
    public Optional<Paciente> findById(Long id) {
        return pacientes.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    // Guardar
    public Paciente save(Paciente paciente) {
        pacientes.add(paciente);
        return paciente;
    }

    // Eliminar
    public boolean deleteById(Long id) {
        return pacientes.removeIf(p -> p.getId().equals(id));
    }

    // Actualizar
    public Paciente update(Long id, Paciente paciente) {

        Optional<Paciente> existente = findById(id);

        if (existente.isPresent()) {

            Paciente p = existente.get();

            p.setNombre(paciente.getNombre());
            p.setApellido(paciente.getApellido());
            p.setEdad(paciente.getEdad());
            p.setTelefono(paciente.getTelefono());

            return p;
        }

        return null;
    }

    
}
