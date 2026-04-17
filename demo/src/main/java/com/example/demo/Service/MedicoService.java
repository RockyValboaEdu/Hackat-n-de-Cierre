package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Medico;
import com.example.demo.Repository.MedicoRepository;

@Service
public class MedicoService implements IMedicoService {

    @Autowired
    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public Optional<Medico> obtenerMedicoPorId(Long idMedico) {
        return medicoRepository.findById(idMedico);
    }

    @Override
    public Medico agregarMedico(Medico medico) {
        if (medico.getNombreCompleto() == null || medico.getEspecialidad() == null) {
            throw new IllegalArgumentException("El nombre completo y la especialidad son obligatorios"); 
        }
        if (medico.getEspecialidad().isEmpty() || medico.getNombreCompleto().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo y la especialidad no pueden estar vacíos");
        }
        if (medico.getIdMedico() == null) {
            throw new IllegalArgumentException("El ID del médico es obligatorio");
        }
        if (medicoRepository.findById(medico.getIdMedico()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un médico con el mismo ID");
            
        }
        return medicoRepository.save(medico);
    }

    @Override
    public Medico actualizarMedico(Long idMedico, Medico medico) {
        return medicoRepository.update(idMedico, medico);
    }

    @Override
    public boolean eliminarMedico(Long idMedico) {
        return medicoRepository.deleteById(idMedico);
    }

    @Override
    public int contarMedicos() {
        return medicoRepository.findAll().size();
    }

}
