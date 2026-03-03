package com.digis01.FCruzProgramacionNCapasWebSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.digis01.FCruzProgramacionNCapasWebSpring.JPA.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}