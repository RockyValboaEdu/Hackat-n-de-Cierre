package com.example.demo.Model;


public class Medico {
    
    private String especialidad;
    private String nombreCompleto;
    private Long idMedico;


    public Medico() {
    }

    public Medico(String especialidad, String nombreCompleto, Long idMedico) {
        this.especialidad = especialidad;
        this.nombreCompleto = nombreCompleto;
        this.idMedico = idMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    

    

}
