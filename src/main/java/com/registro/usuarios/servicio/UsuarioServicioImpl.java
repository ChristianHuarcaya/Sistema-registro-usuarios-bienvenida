package com.registro.usuarios.servicio;

import com.registro.usuarios.dto.UsuarioRegistroDTO;
import com.registro.usuarios.entidad.Rol;
import com.registro.usuarios.entidad.Usuario;
import com.registro.usuarios.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UsuarioServicioImpl implements UsuarioServicio, UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServicioImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		// Verifica si ya existe algún usuario en la base de datos
		boolean esPrimerUsuario = usuarioRepository.count() == 0;

		// Asigna el rol según sea el primer usuario o no
		List<Rol> roles = esPrimerUsuario ? Arrays.asList(new Rol("ROLE_ADMIN")) : Arrays.asList(new Rol("ROLE_USER"));

		Usuario usuario = new Usuario(registroDTO.getNombre(), registroDTO.getApellido(), registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()), roles);

		return usuarioRepository.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o contraseña inválidos");
		}

		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}

	@Override
	public List<Usuario> listarUsuarios() {

		return usuarioRepository.findAll();
	}

	@Override
	public List<Usuario> buscarPorNombreOEmail(String keyword) {
		List<Usuario> porNombre = usuarioRepository.findByNombreContainingIgnoreCase(keyword);
		List<Usuario> porEmail = usuarioRepository.findByEmailContainingIgnoreCase(keyword);

		// Combina ambos resultados sin duplicados
		Set<Usuario> resultado = new HashSet<>();
		resultado.addAll(porNombre);
		resultado.addAll(porEmail);

		return new ArrayList<>(resultado);
	}

}
