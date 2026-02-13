package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;

import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Colonia;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Direccion;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Estado;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Municipio;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Pais;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Rol;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuario {
    
    @Autowired
    private JdbcTemplate JdbcTemplate;
    
    @Override
    public Result GetAll(){
        Result result = new Result();
        
        JdbcTemplate.execute("{CALL UsuarioGetAll(?)}", (CallableStatementCallback<Boolean>) callableStament ->{
        
            callableStament.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            callableStament.execute();
            
            ResultSet resultSet = (ResultSet) callableStament.getObject(1);
            result.objects = new ArrayList<>();
            
            while(resultSet.next()){
                int idUsuario = resultSet.getInt("idUsuario");
                if (!result.objects.isEmpty() && idUsuario == ((Usuario) (result.objects.get(result.objects.size() - 1))).getIdUsuario()){
                    
                    //Se agrega las direccion condo la lista no es vacia y el id concide con un usuario que ya esta en la lista
                    Direccion direccion = new Direccion();
                    direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                    direccion.setCalle(resultSet.getString("Calle"));
                    direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                    direccion.setNumeroIInteriori(resultSet.getString("NumeroIInteriori"));

                    Colonia colonia = new Colonia();
                    colonia.setNombre(resultSet.getString("NombreColonia"));
                    colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                    Municipio municipio = new Municipio();
                    municipio.setNombre(resultSet.getString("NombreMunicipio"));

                    Estado estado = new Estado();
                    estado.setNombre(resultSet.getString("NombreEstado"));

                    Pais pais = new Pais();
                    pais.setIdPais(resultSet.getInt("IdPais"));
                    pais.setNombre(resultSet.getString("NombrePais"));

                    estado.setPais(pais);
                    municipio.setEstado(estado);
                    colonia.setMunicipio(municipio);
                    direccion.setColonia(colonia);
                    
                    ((Usuario)(result.objects.get(result.objects.size()-1))).Direccion.add(direccion);
                }else{
                    
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                    usuario.setSexo(resultSet.getString("Sexo"));
                    usuario.setNombre(resultSet.getString("NombreUsuario"));
                    usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                    usuario.setApellidosMaterno(resultSet.getString("ApellidosMaterno"));
                    usuario.setUserName(resultSet.getString("UserName"));
                    usuario.setPassword(resultSet.getString("Password"));
                    usuario.setCURP(resultSet.getString("CURP"));
                    usuario.setEmail(resultSet.getString("Email"));
                    usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                    usuario.setCelular(resultSet.getString("Celular"));
                    usuario.setTelefono(resultSet.getString("Telefono"));
                    
                    usuario.Rol = new Rol();
                    usuario.Rol.setIdRol(resultSet.getInt("IdRol"));
                    usuario.Rol.setNombreRol(resultSet.getString("NombreRol"));
                    
                    
                    Direccion direccion = new Direccion();
                    direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                    direccion.setCalle(resultSet.getString("Calle"));
                    direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                    direccion.setNumeroIInteriori(resultSet.getString("NumeroIInteriori"));

                    Colonia colonia = new Colonia();
                    colonia.setNombre(resultSet.getString("NombreColonia"));
                    colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                    Municipio municipio = new Municipio();
                    municipio.setNombre(resultSet.getString("NombreMunicipio"));

                    Estado estado = new Estado();
                    estado.setNombre(resultSet.getString("NombreEstado"));

                    Pais pais = new Pais();
                    pais.setIdPais(resultSet.getInt("IdPais"));
                    pais.setNombre(resultSet.getString("NombrePais"));
                    

                    estado.setPais(pais);
                    municipio.setEstado(estado);
                    colonia.setMunicipio(municipio);
                    direccion.setColonia(colonia);

                    usuario.getDireccion().add(direccion);

                    
                    result.objects.add(usuario);
                    
                }
            }
            return true;
        });
        return result;
    }

}
