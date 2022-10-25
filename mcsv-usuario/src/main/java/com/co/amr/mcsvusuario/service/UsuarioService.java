package com.co.amr.mcsvusuario.service;

import java.util.List;
import java.util.Optional;

import com.co.amr.mcsvusuario.model.Usuario;

public interface UsuarioService {

	List<Usuario> findAll();
	
	Optional<Usuario> findById(Long id);
	
	Usuario save(Usuario usuario);
	
	boolean existsByEmail(String email);
	
}
