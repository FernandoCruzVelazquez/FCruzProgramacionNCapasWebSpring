package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

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
public class MunicipioDAOImplementation implements IMunicipio{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result getMunicipioByEstado(int idEstado) {
        Result result = new Result();

        jdbcTemplate.execute("{CALL getMunicipioByEstadoSP(?, ?)}", (CallableStatementCallback<Boolean>) callableStatement -> {
            callableStatement.setInt(1, idEstado);
            callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);

            callableStatement.execute();

            ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
            result.objects = new ArrayList<>();

            while (resultSet.next()) {
                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                municipio.setNombre(resultSet.getString("Nombre"));
                
                Estado estado = new Estado();
                estado.setIdEstado(resultSet.getInt("IdEstado"));
                
                municipio.setEstado(estado); 
                result.objects.add(municipio); 
            }
            
            return result.correct;
        });

        return result;
    }
}
