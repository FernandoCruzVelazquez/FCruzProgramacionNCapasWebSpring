package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario;
import java.util.List;


public interface IUsuario {

    Result GetAll();
    Result Add(Usuario usuario);
    Result AddAll(List<Usuario> usuarios);
    Result Update(Usuario usuario);
    Result Delete(int idUsuario); 
    
    Result GetById(int idUsuario);
    Result GetByFilter(Usuario usuario);
    
    Result UpdateFoto(int idUsuario, String foto);
    
}
