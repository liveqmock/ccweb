/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.ccweb.web.controller;

import com.nms.ccweb.ejb.UserBean;
import com.nms.ccweb.web.util.MessageUtil;
import java.io.Serializable;
import java.security.Principal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
/**
 *
 * @author Cuong
 */
@Named("changePassCtl")
@ViewScoped
public class ChangePasswordController implements Serializable {

    private static final long serialVersionUID = -5689022693900785928L;
    private String username;
    private String password;
    @EJB
    private UserBean userService;

    public ChangePasswordController() {
    }
    
    @PostConstruct
    public void init() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (principal != null) {
            username = principal.getName();
        }
    }
    
    public void changePassword() {
        FacesMessage message; 
        if (username == null) {
            MessageUtil.addGlobalErrorMessage("user-not-login");
        }
        
        try {
            userService.updatePassword(username, password);
            MessageUtil.addGlobalInfoMessage("change-password-success");
        } catch (Exception ex) {
            MessageUtil.addGlobalErrorMessage(ex);
        }
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
