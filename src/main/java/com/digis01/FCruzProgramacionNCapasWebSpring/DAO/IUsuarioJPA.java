package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario;

public interface IUsuarioJPA {

    Result GetAll();
    Result Add(Usuario usuario);
    Result GetById(int idUsuario);
    Result Update(Usuario usuario);
    Result UpdateFoto(int idUsuario, String foto);
}