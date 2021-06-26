package com.tbp.controller;

import com.tbp.client.ViaCEPClient;
import com.tbp.model.Endereco;
import com.tbp.model.Usuario;
import com.tbp.repository.UsuarioRepository;
import com.tbp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registro")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ViaCEPClient viaCEPClient;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Usuario save(@RequestBody Usuario usuario) {
        Endereco endereco = viaCEPClient.buscaEnderecoPor(usuario.getCep());
        usuario.setEndereco(endereco);
        Usuario u = usuarioRepository.save(usuario);
        return u;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Usuario update(@RequestBody Usuario usuario) {
        Endereco endereco = viaCEPClient.buscaEnderecoPor(usuario.getCep());
        usuario.setEndereco(endereco);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioSalvo;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void remove(@RequestBody Usuario usuario) {
        try {
            usuarioRepository.delete(usuario.getId());
        }
        catch(Exception exception) {
            exception.getMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Usuario getById(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findOne(id);
        return usuario;
    }
}