package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Medico;

public interface IMedicoService {

    List<Medico> listarMedicos();

    Optional<Medico> obtenerMedicoPorId(Long idMedico);

    Medico agregarMedico(Medico medico);

    Medico actualizarMedico(Long idMedico, Medico medico);

    boolean eliminarMedico(Long idMedico);

    int contarMedicos();

}
