package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Direccion;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;

public interface IDireccion {
    Result Update(Direccion direccion);
    Result DeleteDireccion(int idDireccion);
}
