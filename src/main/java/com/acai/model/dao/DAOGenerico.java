package com.acai.model.dao;

import java.util.List;

public interface DAOGenerico<E> {
    public void salvar(E e);
    public E buscar(Integer codigo);
    public void alterar(E e);
    public void deletar(E e);
    public List<E> buscarTodos();
}
