package com.tbp.service;

import com.tbp.client.ViaCEPClient;
import com.tbp.model.Endereco;
import com.tbp.model.Usuario;
import com.tbp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository ur;
    @Autowired
    private ViaCEPClient viaCEPClient;

    public UsuarioService(UsuarioRepository ur) {
        this.ur = ur;
    }

    public Usuario register(String nome, String email, String senha,
                            String cep, String username, Endereco endereco) {

        return this.ur.save(new Usuario(nome, senha, email, username, cep, endereco));
    }

    public void update(Long id, Usuario u) {
        u.setId(id);
        ur.save(u);
    }

    public void edit(String nome, String senha, String email, String cep) {
        Usuario usuario = ur.findByEmail(email);
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setCep(cep);
        Endereco endereco = viaCEPClient.buscaEnderecoPor(cep);
        usuario.setEndereco(endereco);
        ur.save(usuario);
    }

    public void editarEmail(String email, String novoemail) {
        Usuario usuario = ur.findByEmail(email);
        usuario.setEmail(novoemail);
        ur.save(usuario);
    }

    public Usuario findUserByEmail(String email){
        Usuario usuario = this.ur.findByEmail(email);
        return usuario;
    }

}
