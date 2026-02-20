package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario;


public interface IUsuario {

    Result GetAll();
    Result Add(Usuario usuario);
    Result Update(Usuario usuario);
    Result Delete(int idUsuario);
    Result GetById(int idUsuario);
    
    Result UpdateFoto(int idUsuario, String foto);
    
}
