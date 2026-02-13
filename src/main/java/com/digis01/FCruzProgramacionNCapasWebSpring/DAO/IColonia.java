package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;

public interface IColonia {
    Result getColoniaByMunicipios(int IdMunicipio);
    
    Result getDireccionByCP(String CodigoPostal);
}
