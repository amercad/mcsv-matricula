package com.co.amr.mcsvusuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.co.amr.mcsvusuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Override
	@Transactional(readOnly = true)
	@Query("SELECT u FROM Usuario u WHERE u.activo = true")
	List<Usuario> findAll();
	
	@Override
	@Transactional(readOnly = true)
	@Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.activo = true")
	Optional<Usuario> findById(Long id);
	
	@Transactional(readOnly = true)
	boolean existsByEmail(String email);

}
