package com.registro.usuarios.servicio;

import java.util.List;

import com.registro.usuarios.dto.UsuarioRegistroDTO;
import com.registro.usuarios.entidad.Usuario;

public interface UsuarioServicio {

    Usuario guardar(UsuarioRegistroDTO registroDTO);

    List<Usuario> listarUsuarios();
    List<Usuario> buscarPorNombreOEmail(String keyword);

}
