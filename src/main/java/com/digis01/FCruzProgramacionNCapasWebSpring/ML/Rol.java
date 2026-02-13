package com.digis01.FCruzProgramacionNCapasWebSpring.ML;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Rol {
    
    @NotNull(message = "No puedo ser nulo")
    private Integer IdRol;
    private String NombreRol;
    
    public Integer getIdRol(){
        return IdRol;
    }
    
    public void setIdRol(Integer IdRol){
        this.IdRol = IdRol;
    }
    
    public String getNombreRol(){
        return NombreRol;
    }
    
    public void setNombreRol(String NombreRol){
        this.NombreRol = NombreRol;
    }
}
