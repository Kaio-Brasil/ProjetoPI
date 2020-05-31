package com.acai.model.entidadedao;

import com.acai.model.dao.DAOGenerico;
import java.util.List;

public interface ProdutoDAO<Produto> extends DAOGenerico<Produto> {
    public List<Produto> listarProdutoDisponivel();
}
