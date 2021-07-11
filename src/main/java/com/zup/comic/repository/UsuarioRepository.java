package com.zup.comic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zup.comic.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByNomeContainingIgnoreCase(String nome);

	Usuario findByEmail(String email);

	Usuario findByCpf(String cpf);
	
	


	
	

}
