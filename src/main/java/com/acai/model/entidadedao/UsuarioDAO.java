package com.acai.model.entidadedao;

import com.acai.model.dao.DAOGenerico;

public interface UsuarioDAO<Usuario> extends DAOGenerico<Usuario> {
    public Usuario autenticar(String login, String senha);

}
