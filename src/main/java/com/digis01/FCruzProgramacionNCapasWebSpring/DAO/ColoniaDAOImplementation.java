package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Colonia;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Estado;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Municipio;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Pais;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaDAOImplementation implements IColonia {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result getColoniaByMunicipios(int IdMunicipio) {
        Result result = new Result();

        jdbcTemplate.execute("{CALL getColoniasByMunicipioSP(?, ?)}", (CallableStatementCallback<Boolean>) callableStatement -> {
            callableStatement.setInt(1, IdMunicipio);
            callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);

            callableStatement.execute();

            ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
            result.objects = new ArrayList<>();

            while (resultSet.next()) {
                
                Colonia colonia = new Colonia();                
                colonia.setIdColonia(resultSet.getInt("IdColonia"));
                colonia.setNombre(resultSet.getString("Nombre"));
                colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                
                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                
                colonia.setMunicipio(municipio);
                result.objects.add(colonia); 
                 
            }
            
            return result.correct;
        });

        return result;
    }
    
    @Override
    public Result getDireccionByCP(String codigoPostal) {
        Result result = new Result();

        jdbcTemplate.execute("{CALL GetDireccionBycpSP(?, ?)}",
            (CallableStatementCallback<Boolean>) callableStatement -> {

                callableStatement.setString(1, codigoPostal);
                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);
                callableStatement.execute();

                ResultSet rs = (ResultSet) callableStatement.getObject(2);
                result.objects = new ArrayList<>();

                while (rs.next()) {

                    Colonia colonia = new Colonia();
                    colonia.setIdColonia(rs.getInt("IdColonia"));
                    colonia.setNombre(rs.getString("Colonia"));
                    colonia.setCodigoPostal(rs.getString("CodigoPostal"));

                    Municipio municipio = new Municipio();
                    municipio.setIdMunicipio(rs.getInt("IdMunicipio"));
                    municipio.setNombre(rs.getString("Municipio"));

                    Estado estado = new Estado();
                    estado.setIdEstado(rs.getInt("IdEstado"));
                    estado.setNombre(rs.getString("Estado"));

                    Pais pais = new Pais();
                    pais.setIdPais(rs.getInt("IdPais"));
                    pais.setNombre(rs.getString("Pais"));

                    estado.setPais(pais);
                    municipio.setEstado(estado);
                    colonia.setMunicipio(municipio);

                    result.objects.add(colonia);
                }

                result.correct = true;
                return true;
            });

        return result;
    }

}
