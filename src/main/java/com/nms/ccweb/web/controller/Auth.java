/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nms.ccweb.web.controller;

import com.nms.ccweb.ejb.UserBean;
import com.nms.ccweb.entity.security.User;
import com.nms.ccweb.web.util.MessageUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nguyen Trong Cuong
 * @since 09/05/2014
 * @version 1.0
 */
@Named("authCtl")
@ViewScoped
public class Auth implements Serializable {
    
    private static final long serialVersionUID = -3542801712367764523L;
    private String username;
    private String password;
    private String originalURL;
    @EJB
    private UserBean userBean;
    
    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
        
        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);
            
            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }
    
    public void login() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        try {
            request.login(username, password);
            User user = userBean.find(username, password);
            externalContext.getSessionMap().put("user", user);
            externalContext.redirect(originalURL);
        } catch (ServletException e) {
            // handle unknow username/password in request.login()
            String errorMessage = MessageUtil.getBundleMessage("user-pass-incorrect");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
            facesContext.addMessage(null, message);
        }
    }
    
    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }
    
    /* Getters and Setters */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
