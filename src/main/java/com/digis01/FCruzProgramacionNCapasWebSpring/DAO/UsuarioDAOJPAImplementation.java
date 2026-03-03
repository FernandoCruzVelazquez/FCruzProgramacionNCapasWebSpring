package com.digis01.FCruzProgramacionNCapasWebSpring.DAO;


import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Usuario;
import com.digis01.FCruzProgramacionNCapasWebSpring.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UsuarioDAOJPAImplementation implements IUsuarioJPA {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Result GetAll() {

        Result result = new Result();

        try {
            List<Usuario> usuarios = usuarioRepository.findAll();

            result.correct = true;
            result.objects = (List<Object>) (List<?>) usuarios;
            System.out.println("Usuarios encontrados: " + usuarios.size());

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
        }

        return result;
    }
}