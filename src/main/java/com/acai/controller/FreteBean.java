package com.acai.controller;

import com.acai.model.entidade.Frete;
import com.acai.model.regradenegocio.FreteRN;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class FreteBean {
    private FreteRN freteRN = null;
    private Frete selectedFrete;
    private Frete cadastrarFrete;

    public FreteBean() {
        this.freteRN = new FreteRN(FreteRN.HIBERNATE_FRETE_DAO);
        this.cadastrarFrete = new Frete();
    }
    
    public void adicionarFreteAction() {
        this.freteRN.salvarFrete(this.cadastrarFrete);
        this.cadastrarFrete = new Frete();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Frete cadastrado com sucesso!"));
    }
    
    public Frete buscarFreteAction(Integer codigo) {
        return (Frete) this.freteRN.buscarFrete(codigo);
    }
    
    public void alterarFreteAction() {
        this.freteRN.alterarFrete(this.selectedFrete);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ferte alterado com sucesso!"));
    }
    
    public void excluirFreteAction() {
        this.freteRN.deletarFrete(this.selectedFrete);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Frete deletado com sucesso!"));
    }
    
    public List<Frete> listarAction() {
        return this.freteRN.buscarTodos();
    }

    public FreteRN getFreteRN() {
        return freteRN;
    }

    public void setFreteRN(FreteRN freteRN) {
        this.freteRN = freteRN;
    }
    
    public Frete getSelectedFrete() {
        return this.selectedFrete;
    }

    public void setSelectedFrete(Frete selectedFrete) {
        this.selectedFrete = selectedFrete;
    }

    public Frete getCadastrarFrete() {
        return cadastrarFrete;
    }

    public void setCadastrarFrete(Frete cadastrarFrete) {
        this.cadastrarFrete = cadastrarFrete;
    }
    
}
