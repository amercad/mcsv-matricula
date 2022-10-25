package com.co.amr.mcsvusuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.amr.mcsvusuario.model.Usuario;
import com.co.amr.mcsvusuario.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}

}
