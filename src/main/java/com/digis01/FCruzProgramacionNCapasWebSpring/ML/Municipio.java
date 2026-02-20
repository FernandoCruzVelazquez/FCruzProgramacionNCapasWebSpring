package com.digis01.FCruzProgramacionNCapasWebSpring.ML;

public class Municipio {
    
    private Integer IdMunicipio;
    private String Nombre;
    public Estado Estado;
    
    public Integer  getIdMunicipio(){
        return IdMunicipio;
    }
    
    public void setIdMunicipio(Integer IdMunicipio){
        this.IdMunicipio = IdMunicipio;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }

    public Estado getEstado() {
        return Estado;
    }

    public void setEstado(Estado Estado) {
        this.Estado = Estado;
    }

    
}
