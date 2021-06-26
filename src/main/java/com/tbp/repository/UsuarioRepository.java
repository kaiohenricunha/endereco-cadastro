package com.tbp.repository;

import com.tbp.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    List<Usuario> findByNome(String nome);

    Usuario findByEmail(String email);
}
