package com.acai.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kaio
 */
public class AutenticacaoPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        String pagina = facesContext.getViewRoot().getViewId();
        
        boolean estaLogado = (pagina.lastIndexOf("index.xhtml") > -1);
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object cliente = session.getAttribute("clienteLogado");
        
        if(!estaLogado && cliente == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você não está Logado!"));
            NavigationHandler navigationHandlerna = facesContext.getApplication().getNavigationHandler();
            navigationHandlerna.handleNavigation(facesContext, null, "index.xhtml?faces-redirect=true");
        }
        
    }

    @Override
    public void beforePhase(PhaseEvent phaseListener) { }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
