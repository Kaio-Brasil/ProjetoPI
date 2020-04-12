package com.acai.model.entidadedao;

import com.acai.model.dao.DAOGenerico;

public interface ClienteDAO<Cliente> extends DAOGenerico<Cliente> {
    public Cliente autenticar(String login, String senha);
}
