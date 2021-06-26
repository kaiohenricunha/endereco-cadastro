package com.tbp.security;
import com.tbp.model.Usuario;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UsuarioSession {

    Usuario usuario;

    public void addLoggedUser(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getLoggedUser() {
        return usuario;
    }

    public void removeLoggedUser() {
        this.usuario = null;
    }

}
