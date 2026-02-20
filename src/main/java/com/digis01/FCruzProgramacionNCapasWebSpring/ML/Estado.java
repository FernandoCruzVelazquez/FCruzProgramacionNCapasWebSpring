package com.digis01.FCruzProgramacionNCapasWebSpring.ML;

import java.util.List;

public class Estado {
    
    private Integer IdEstado;
    private String Nombre;
    public Pais Pais;

    
    public Integer  getIdEstado(){
        return IdEstado;
    }
    
    public void setIdEstado(Integer IdEstado){
        this.IdEstado = IdEstado;
    }
    
    public String getNombre (){
        return Nombre;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }

    public Pais getPais() {
        return Pais;
    }

    public void setPais(Pais Pais) {
        this.Pais = Pais;
    }

    

    

    
    
}
