package com.acai.util;

import com.acai.controller.AutenticarBean;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kaio
 */

@WebFilter
public class FiltroSeguranca implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String contextPath = httpRequest.getContextPath();
        AutenticarBean autenticarBean = (AutenticarBean) httpRequest.getSession().getAttribute("clienteLogado");
        
        if(autenticarBean == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você não está Logado!"));
            httpResponse.sendRedirect(contextPath + "index.xhtml?faces-redirect=true");
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
    
}
