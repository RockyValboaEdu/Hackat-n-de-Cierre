package com.example.demo.Service;

import com.example.demo.Model.Paciente;
import java.util.Optional;
import com.example.demo.Repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente agregarPaciente(Paciente paciente) {

        if (paciente.getNombre() == null || paciente.getApellido() == null) {
            throw new IllegalArgumentException("El nombre y apellido son obligatorios");
        }

        if (paciente.getNombre().isEmpty() || paciente.getApellido().isEmpty()) {
            throw new IllegalArgumentException("El nombre y apellido no pueden estar vacíos");
        }

        if (paciente.getId() == null) {
            throw new IllegalArgumentException("El ID del paciente es obligatorio");
        }

        if (pacienteRepository.findById(paciente.getId()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un paciente con ese ID");
        }

        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Long id, Paciente paciente) {
        return pacienteRepository.update(id, paciente);
    }

    @Override
    public boolean eliminarPaciente(Long id) {
        return pacienteRepository.deleteById(id);
    }

    @Override
    public int contarPacientes() {
        return pacienteRepository.findAll().size();
    }



}
