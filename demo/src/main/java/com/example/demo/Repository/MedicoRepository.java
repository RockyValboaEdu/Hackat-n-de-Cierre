package com.example.demo.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.Medico;

@Repository
public class MedicoRepository {

    private final List<Medico> medicos = new ArrayList<>();

    private final AtomicLong contadorId = new AtomicLong();

    public MedicoRepository() {
        medicos.add(new Medico("Medico general", "Dr. Juan Perez", 1L));
        medicos.add(new Medico("Quirurgico", "Dr. Armando Paredes", 2L));
        medicos.add(new Medico("Neurologico", "Dr. Maria Lopez", 3L));
    }

    public List<Medico> findAll() {
        return medicos;
    }

    public Optional<Medico> findById(Long id) {
        return medicos.stream().filter(m -> m.getIdMedico().equals(id)).findFirst();
    }

    public Medico save(Medico medico){
        medicos.add(medico);
        return medico;
    }

    public boolean deleteById(Long id) {
        return medicos.removeIf(m -> m.getIdMedico().equals(id));
    }

    public Medico update(Long id, Medico medico) {
        Optional<Medico> medicoExistente = findById(id);
        if (medicoExistente.isPresent()) {
            Medico m = medicoExistente.get();
            m.setNombreCompleto(medico.getNombreCompleto());
            m.setEspecialidad(medico.getEspecialidad());
            return m;
        }
        return null;
    }

}
