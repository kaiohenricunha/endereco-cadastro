package com.tbp.security;

import com.tbp.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    UsuarioSession usuarioSession;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
       String url = httpServletRequest.getRequestURI();
        Usuario usuario = usuarioSession.getLoggedUser();
        if(usuario != null) {
            return true;
        } else if(url.contains("/spring-mvc/registro") || url.contains("/usuario/create") || url.contains("login") || url.equals("/spring-mvc/")) {
            return true;
        }
        String loginPage = httpServletRequest.getContextPath() + "/login/doLogin";
        httpServletResponse.sendRedirect(loginPage);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
