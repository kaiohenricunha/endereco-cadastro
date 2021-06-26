package com.tbp.controller;

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
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioSession usuarioSession;

    @RequestMapping(value = "doLogin", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("senha") String senha,
                          Map<String, Object> model) {

        Usuario u = usuarioRepository.findByUsername(username);
        if(u != null && u.getSenha().equals(senha)) {
            usuarioSession.addLoggedUser(u);
            return "redirect:/usuario/list";
        } else {
            model.put("message", "Usuário/senha inválido(a)");
            return "login";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        usuarioSession.removeLoggedUser();
        return "login";
    }
}
