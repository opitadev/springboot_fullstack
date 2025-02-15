package com.yomdev.ecommerce.service.impl;

import com.yomdev.ecommerce.model.Usuario;
import com.yomdev.ecommerce.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    HttpSession session;

    private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Este es el username");
        Optional<Usuario> optionalUser=usuarioService.findByEmail(username);
        if (optionalUser.isPresent()) {
            log.info("Esto es el id del usuario: {}", optionalUser.get().getId());
            session.setAttribute("idusuario", optionalUser.get().getId());
            Usuario usuario= optionalUser.get();
            return User.builder().username(usuario.getNombre()).password(bCrypt.encode(usuario.getPassword())).roles(usuario.getTipo()).build();
        }else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

}
