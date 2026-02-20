package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Direccion;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import java.sql.CallableStatement;
import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DireccionDAOImplementation implements IDireccion {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result Update(Direccion direccion) {

        Result result = new Result();

        try {

            jdbcTemplate.update(
                "CALL DireccionUpdateSP(?,?,?,?,?)",

                direccion.getIdDireccion(),
                direccion.getCalle(),
                direccion.getNumeroIInteriori(),
                direccion.getNumeroExterior(),
                direccion.getColonia().getIdColonia()
            );

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            ex.printStackTrace();
        }

        return result;
    }
}
