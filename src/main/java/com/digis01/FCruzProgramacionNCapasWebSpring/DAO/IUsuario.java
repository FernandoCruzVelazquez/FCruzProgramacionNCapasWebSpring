package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario;


public interface IUsuario { //aqui es donde se forma el motodo en la capa de interfaz

    Result GetAll();
    public Result Add(Usuario usuario);
    
}
