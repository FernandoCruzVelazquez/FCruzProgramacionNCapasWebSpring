package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;


import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Usuario;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Direccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuarioDAOJPAImplementation implements IUsuarioJPA {

    @Autowired
    private EntityManager entityManager; 
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public Result GetAll() {
        Result result = new Result();

        try {

            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario", Usuario.class);

            List<Usuario> usuariosJPA = queryUsuario.getResultList();

            result.objects = new ArrayList<>(); 

            for (Usuario usuarioJPA : usuariosJPA) {

                com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario usuarioML = modelMapper.map(usuarioJPA,com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario.class);

                result.objects.add(usuarioML); 
            }

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    @Transactional
    public Result Add(com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario usuarioML) {

        Result result = new Result();

        try {

            com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Usuario usuarioJPA = modelMapper.map(usuarioML, com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Usuario.class);

            if (usuarioML.getRol() != null && usuarioML.getRol().getIdRol() > 0) {

                com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Rol rolJPA = entityManager.find(com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Rol.class,usuarioML.getRol().getIdRol() );

                usuarioJPA.setRol(rolJPA);
            }

            if (usuarioJPA.getDireccion() != null) {
                for (com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Direccion direccion : usuarioJPA.getDireccion()) {

                    direccion.setUsuario(usuarioJPA); 
                }
            }

            
            entityManager.persist(usuarioJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
    
    @Override
    public Result GetById(int idUsuario) {
        Result result = new Result();
        try {
            
            TypedQuery<com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Usuario> query = entityManager.createQuery(
                "SELECT usuario FROM Usuario usuario " +
                "LEFT JOIN FETCH usuario.rol rol " +
                "LEFT JOIN FETCH usuario.direccion direccion " +
                "LEFT JOIN FETCH direccion.colonia colonia " +
                "LEFT JOIN FETCH colonia.municipio municipio " +
                "LEFT JOIN FETCH municipio.estado estado " +
                "LEFT JOIN FETCH estado.pais " +
                "WHERE usuario.idUsuario = :id",
                Usuario.class);
            query.setParameter("id", idUsuario);
            com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Usuario usuarioJPA = query.getSingleResult();

            com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario usuarioML = 
                modelMapper.map(usuarioJPA, com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario.class);

            if (usuarioML.getDireccion() != null) {
                for (Direccion d : usuarioML.getDireccion()) {
                    d.setUsuario(null); 
                }
            }

            result.object = usuarioML;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = "No se encontró el usuario";
        }
        return result;
    }
    
    @Override
    @Transactional 
    public Result Update(com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario usuarioML) {
        Result result = new Result();
        try {
            
            com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Usuario usuarioJPA = modelMapper.map(usuarioML, com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Usuario.class);

            entityManager.merge(usuarioJPA);

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
        }
        return result;
    }
    
    @Override
    public Result UpdateFoto(int idUsuario, String foto) {

        Result result = new Result();

        try {
            
            
            
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
        }

        return result;
    }
    
}