package com.example.demo.Service;

import com.example.demo.Model.Paciente;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    List<Paciente> listarPacientes();

    Optional<Paciente> obtenerPacientePorId(Long id);

    Paciente agregarPaciente(Paciente paciente);

    Paciente actualizarPaciente(Long id, Paciente paciente);

    boolean eliminarPaciente(Long id);

    int contarPacientes();
}
