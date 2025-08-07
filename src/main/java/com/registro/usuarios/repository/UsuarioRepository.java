package com.registro.usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.entidad.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByEmail(String email);
List<Usuario> findByNombreContainingIgnoreCase(String nombre);
List<Usuario> findByEmailContainingIgnoreCase(String email);
 

}
