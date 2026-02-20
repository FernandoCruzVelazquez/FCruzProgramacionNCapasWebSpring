package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Estado;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Pais;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOImplementation implements IEstado {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result getEstadosByPais(int idPais) {
        Result result = new Result();

        jdbcTemplate.execute("{CALL GetEstadosByPaisSP(?, ?)}", (CallableStatementCallback<Boolean>) callableStatement -> {
            callableStatement.setInt(1, idPais);
            callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);

            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(2);
            result.objects = new ArrayList<>();

            while (rs.next()) {
                Estado estado = new Estado();
                estado.setIdEstado(rs.getInt("IdEstado"));
                estado.setNombre(rs.getString("Nombre"));

                Pais pais = new Pais();
                pais.setIdPais(rs.getInt("IdPais"));
                estado.setPais(pais);

                result.objects.add(estado);
            }
            
            return result.correct;
        });

        return result;
    }

}


