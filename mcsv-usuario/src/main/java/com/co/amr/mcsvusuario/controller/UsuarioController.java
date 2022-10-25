package com.co.amr.mcsvusuario.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.amr.mcsvusuario.model.Usuario;
import com.co.amr.mcsvusuario.service.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		
		Optional<Usuario> usuarioDB = service.findById(id);
		
		if (usuarioDB.isPresent()) {
			return ResponseEntity.ok(usuarioDB.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Map<String, ? extends Object>> save(@Valid @RequestBody Usuario usuario, BindingResult result) {
		
		if (result.hasErrors()) {
			return validFields(result);
		}
		
		if (service.existsByEmail(usuario.getEmail())) {
			return mensajeError();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("Save", service.save(usuario)));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, ? extends Object>> update(@Valid @RequestBody Usuario usuario, BindingResult result,
		@PathVariable Long id) {
		
		Optional<Usuario> usuarioDB = service.findById(id);
		
		if (usuarioDB.isPresent()) {
			if (result.hasErrors()) {
				return validFields(result);
			}
			
			Usuario usuarioUpdate = usuarioDB.get();
			
			if (!usuarioUpdate.getEmail().equalsIgnoreCase(usuario.getEmail()) 
				&& service.existsByEmail(usuario.getEmail())) {
				return mensajeError();
			}
			
			usuarioUpdate.setNombre(usuario.getNombre());
			usuarioUpdate.setEmail(usuario.getEmail());
			usuarioUpdate.setPassword(usuario.getPassword());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("Update", service.save(usuarioUpdate)));
		}
		
		return ResponseEntity.notFound().build();
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		Optional<Usuario> usuarioDB = service.findById(id);
		
		if (usuarioDB.isPresent()) {			
			Usuario usuarioUpdate = usuarioDB.get();
			
			usuarioUpdate.setActivo(Boolean.FALSE);
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
		
		
	}
	
	private ResponseEntity<Map<String, ? extends Object>> mensajeError() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
			Collections.singletonMap("mensaje", "El email ingresado ya esta registrado!"));
	}
	
	private ResponseEntity<Map<String, ? extends Object>> validFields(BindingResult result) {
		Map<String, String> response = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			response.put(err.getField(), "El campo ".concat(err.getField()).concat(" ")
				.concat(err.getDefaultMessage()));
		});
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
}
