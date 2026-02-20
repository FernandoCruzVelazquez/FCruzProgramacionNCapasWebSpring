package com.digis01.FCruzProgramacionNCapasWebSpring.ML;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;

public class Direccion {
    
    private int IdDireccion;
    @NotNull(message = "No puedo ser nulo")
    @NotEmpty(message = "No puedo ser vacio")
    private String Calle;
    @NotNull(message = "No puedo ser nulo")
    @NotEmpty(message = "No puedo ser vacio")
    private String NumeroIInteriori;
    @NotNull(message = "No puedo ser nulo")
    @NotEmpty(message = "No puedo ser vacio")
    private String NumeroExterior;
    public Colonia Colonia;
    public Usuario Usuario;
    
    public int getIdDireccion(){
        return IdDireccion;
    }
    
    public void setIdDireccion(int IdDireccion){
        this.IdDireccion = IdDireccion;
    }
    
    public String getCalle(){
        return Calle;
    }
    
    public void setCalle(String Calle){
        this.Calle = Calle;
    }
    
    public String getNumeroIInteriori (){
        return NumeroIInteriori;
    }
    
    public void setNumeroIInteriori(String NumeroIInteriori){
        this.NumeroIInteriori = NumeroIInteriori;
    }
    
    public String getNumeroExterior(){
        return NumeroExterior;
    }
    
    public void setNumeroExterior(String NumeroExterior){
        this.NumeroExterior = NumeroExterior;
    }

    public Colonia getColonia() {
        return Colonia;
    }

    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }

}
