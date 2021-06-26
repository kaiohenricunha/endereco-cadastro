package com.tbp.controller;

import com.tbp.client.ViaCEPClient;
import com.tbp.model.Endereco;
import com.tbp.model.Usuario;
import com.tbp.repository.UsuarioRepository;
import com.tbp.security.UsuarioSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioSession usuarioSession;
    @Autowired
    private ViaCEPClient viaCEPClient;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String telaCadastroUsuario() {
        return "/usuario/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String acaoSalvarUsuario(Map<String, Object> model,
                                    @RequestParam("nome") String nome,
                                    @RequestParam("senha") String senha,
                                    @RequestParam("email") String email,
                                    @RequestParam("username") String username,
                                    @RequestParam("cep") String cep) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setEmail(email);
        usuario.setUsername(username);
        usuario.setCep(cep);
        Endereco endereco = viaCEPClient.buscaEnderecoPor(cep);
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);
        String mensagem = "Usuário " + nome + " / " + email + " / " + username + " / " + cep + " / salvo.";
        model.put("mensagem", mensagem);
        return "/usuario/create";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listar(Map<String, Object> model) {
            Iterable<Usuario> todosUsuarios = usuarioRepository.findAll();
            model.put("usuarioLista", todosUsuarios);
            model.put("usuarioSession", usuarioSession);
            return "/usuario/list";
    }

    @RequestMapping(value = "endereco", method = RequestMethod.GET)
    public String listarEndereco(Map<String, Object> model) {
        Iterable<Usuario> todosUsuarios = usuarioRepository.findAll();
        model.put("usuarioLista", todosUsuarios);
        model.put("usuarioSession", usuarioSession);
        return "/usuario/endereco";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editar(@RequestParam("id") Long id, Map<String, Object> model) {
        Usuario usuario = usuarioRepository.findOne(id);
        model.put("usuario", usuario);
        return "/usuario/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public void editar(@RequestParam("nome") String nome, @RequestParam("id") Long id,
                       @RequestParam("email") String email,
                       @RequestParam("username") String username,
                       @RequestParam("senha") String senha,
                       @RequestParam("cep") String cep,
                       Map<String, Object> model) {
        Usuario usuario = usuarioRepository.findOne(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setUsername(username);
        usuario.setSenha(senha);
        usuario.setCep(cep);
        Endereco endereco = viaCEPClient.buscaEnderecoPor(cep);
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);
        model.put("usuario", usuario);
        String message = "Usuário " + nome + " / " + email + " / " + username + " / " + cep + " / salvo.";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deletar(@RequestParam("id") Long id) {
        usuarioRepository.delete(id);
        return "redirect:/usuario/list";
    }
}
