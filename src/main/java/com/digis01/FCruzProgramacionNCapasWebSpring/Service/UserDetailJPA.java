package com.digis01.FCruzProgramacionNCapasWebSpring.Service;

import com.digis01.FCruzProgramacionNCapasWebSpring.DAO.UsuarioDAOJPAImplementation;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Result;
import com.digis01.FCruzProgramacionNCapasWebSpring.ML.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailJPA implements UserDetailsService{
    
    private final UsuarioDAOJPAImplementation usuarioDAOJPAImplementation;
    
    public UserDetailJPA( UsuarioDAOJPAImplementation usuarioDAOJPAImplementation){
        this.usuarioDAOJPAImplementation = usuarioDAOJPAImplementation;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result result = usuarioDAOJPAImplementation.GetByUsername(username);

        if (result.object == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        Usuario usuario = (Usuario) result.object;

        return User.withUsername(usuario.getUserName()) 
                .password(usuario.getPassword())
                .roles(usuario.getRol().getNombreRol())
                .build();
    }
    
}
